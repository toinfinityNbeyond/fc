////package org.zerock.fc.controller;
////
////import javassist.tools.reflect.Reflection;
////import org.reflections.Reflections;
////import org.reflections.scanners.TypeAnnotationsScanner;
////import org.zerock.fc.annotations.Controller;
////
////import javax.servlet.*;
////import javax.servlet.http.*;
////import javax.servlet.annotation.*;
////import java.io.IOException;
////import java.util.Iterator;
////import java.util.Set;
////
////@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
////public class FrontController extends HttpServlet {
////    @Override
////    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        System.out.println("-------------------------------");
////
////        String path = req.getRequestURI();
////        System.out.println(path);
////
////        Reflections reflections = new Reflection("org.zerock.fc.controller.sub", TypeAnnotationsScanner.class);
////        Set<Class<?>> annotated =  reflections.getTypesAnnotatedWith(Controller.class);  //어노테이션이 걸려있는 애들을 찾아. 어노테이션이 없으면 못찾음
////
////        System.out.println(annotated);
////
////        //iterator 루프 돌려고 하는 것.
////        // 어떤 애인지 몰라서 물음표
////        Iterator<Class<?>> it = annotated.iterator();
////
////
////
////    }
////}
//
//package org.zerock.fc.controller;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.zerock.fc.annotations.GetMapping;
//import org.zerock.fc.annotations.PostMapping;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Iterator;
//import java.util.Set;
//
//@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
//@MultipartConfig
//public class FrontController extends HttpServlet {
//
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("-------------------------------");
//
//        request.setCharacterEncoding("UTF-8");
//
//        String path = request.getRequestURI();
//        System.out.println(path);
//
//        //리플렉션으로 동작. 리플렉션이 없었다면 if -else 를 사용해야함.
//        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", MethodAnnotationsScanner.class);
//
//        Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);
//        Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);
//
//        //iterator 루프 돌려고 하는 것.
//        Iterator<Method> getIt = getMethods.iterator();
//
//        while(getIt.hasNext()){
//
//            Method method = getIt.next();
//
//            String value = method.getAnnotation(GetMapping.class).value();
//
//            System.out.println(value);
//
//            if(value.equals(path)){
//                System.out.println("find method success ");
//
//                Class<?> clz = method.getDeclaringClass();
//
//                System.out.println(clz);
//
//                try {
//                    Object obj =clz.getConstructor(null).newInstance(null);
//                    System.out.println(obj);
//
//                    String result = (String) method.invoke(obj, request,response);
//
//
//                    if(result != null){
//                        if(result.startsWith("re:")){
//                            response.sendRedirect(result.substring(3));
//                            break;
//                        }else{
//                            request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
//                            break;
//                        }
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }//end if
//        }//while
//
//
//        Iterator<Method> postIt = postMethods.iterator();
//
//        while(postIt.hasNext()){
//
//            Method method = postIt.next();
//
//            String value = method.getAnnotation(PostMapping.class).value();
//
//            System.out.println(value);
//
//            if(value.equals(path)){
//                System.out.println("find method success ");
//
//                Class<?> clz = method.getDeclaringClass();
//
//                System.out.println(clz);
//
//                try {
//                    Object obj =clz.getConstructor(null).newInstance(null);
//                    System.out.println(obj);
//
//                    String result = (String) method.invoke(obj, request,response);
//
//
//                    if(result != null){
//                        if(result.startsWith("re:")){
//                            response.sendRedirect(result.substring(3));
//                            break;
//                        }else{
//                            request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
//                            break;
//                        }
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }//end if
//        }//while
//
//
//    }
//}


package org.zerock.fc.controller;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
@MultipartConfig
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------------------------------");

        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        System.out.println(path);

        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", MethodAnnotationsScanner.class);

        String methodType = request.getMethod();

        if(methodType.equals("GET")){
            Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);
            Iterator<Method> getIt = getMethods.iterator();

            while(getIt.hasNext()){

                Method method = getIt.next();

                String value = method.getAnnotation(GetMapping.class).value();

                System.out.println(value);

                if(value.equals(path)){
                    System.out.println("find method success ");

                    Class<?> clz = method.getDeclaringClass();

                    System.out.println(clz);

                    try {
                        Object obj =clz.getConstructor(null).newInstance(null);
                        System.out.println(obj);

                        String result = (String) method.invoke(obj, request,response);


                        if(result != null){
                            if(result.startsWith("re:")){
                                response.sendRedirect(result.substring(3));
                                break;
                            }else{
                                request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//end if
            }//while
        }else {
            Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);

            Iterator<Method> postIt = postMethods.iterator();

            while(postIt.hasNext()){

                Method method = postIt.next();

                String value = method.getAnnotation(PostMapping.class).value();

                System.out.println(value);

                if(value.equals(path)){
                    System.out.println("find method success ");

                    Class<?> clz = method.getDeclaringClass();

                    System.out.println(clz);

                    try {
                        Object obj =clz.getConstructor(null).newInstance(null);
                        System.out.println(obj);

                        String result = (String) method.invoke(obj, request,response);


                        if(result != null){
                            if(result.startsWith("re:")){
                                response.sendRedirect(result.substring(3));
                                break;
                            }else{
                                request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                                break;
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//end if
            }//while
        }





    }
}