package org.zerock.fc.controller.sub;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;
import org.zerock.fc.dto.PageMaker;
import org.zerock.fc.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Log4j2
@Controller(value = "/board")
public class BoardController {

    private Integer getInt(String str) {
        try {
            int value = Integer.parseInt(str);
            if (value <= 0) {
                return null;
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/board/read.do")
    public String readGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register get....do");

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {pageDTO.setPage(page);};
        if (size != null) {pageDTO.setSize(size);};

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO" ,boardDTO);
        request.setAttribute("pageDTO",pageDTO);  // 택배 포장


        return "board/read"; //파일경로
    }



    @GetMapping("/board/register.do")
    public String registerGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register get....do");


        return "board/register"; //파일경로
    }


    @PostMapping("/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .writer(request.getParameter("writer"))
                .build();

        Integer bno = BoardDAO.INSTANCE.insert(boardDTO);


        return "re:/board/list.do"; // response.sendRedirect("/board/list?bno="+bno);의 기능
    }


    @GetMapping(value = "/board/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("---------------------------");


        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));   // 두 개 수집해서 PageDTO 만든다.

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {
            pageDTO.setPage(page);
        }
        ;
        if (size != null) {
            pageDTO.setSize(size);
        }

        log.info("==============================1");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("==============================2");
        log.info(dtoList);

        request.setAttribute("dtoList", dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),1230);

        request.setAttribute("pageMaker",pageMaker);


        request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request, response);

        return "board/list";
    }



    @GetMapping(value = "/board/update.do")
    public String updateGet(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("---------------------------");


        return "board/list";
    }

    @PostMapping("/board/update.do")
    public String updatePost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");


        return "re:/board/list.do";
    }

    @PostMapping("/board/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");


        return "re:/board/list.do";
    }
}