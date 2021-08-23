<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-18
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    let num = '${param.bno}' // 새로작성한 글번호가 몇번인지 url에 파라미터가 경고창에 뜬다. -> 경고창의 메시지가 url 새로운 번호가 부여된다.

    if(num){
        alert(num)
        window.history.replaceState(null,'','/board/list'); // 링크창 안뜨게 막아준다. 뒤에 경로로 간다.
    }
</script>
<h1>List Page</h1>

<h4>${pageMaker}</h4>

<form action="/board/list.do" method="get">
    <input type="hidden" name="page" value="1">
    <select name="size">
        <option value="10" ${pageMaker.size ==10? "selected":""}>10</option>
        <option value="20" ${pageMaker.size ==20? "selected":""}>20</option>
        <option value="50" ${pageMaker.size ==50? "selected":""}>50</option>
        <option value="100" ${pageMaker.size ==100? "selected":""}>100</option>
    </select>
    <button type="submit">적용</button>
</form>


<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <div>
            <div>${dto.bno}</div>
            <div><a href="/board/read.do?bno=${dto.bno}&page=${pageMaker.page}&size=${pageMaker.size}"> ${dto.title} </a></div>
            <div>${dto.viewcount}</div>
            </div>
        </li>
    </c:forEach>
</ul>

<hr/>

<style>
    .pageList {
        list-style: none;
        display: flex;
        flex-direction: row;
    }
    .pageList li {
        margin-left: 0.3em;
        background-color: green;
        font-family: "Roboto Light";
        border: 1px solid greenyellow;
    }
    .current {
       font-size: large;
    }

</style>
<ul class="pageList">
    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list.do?page=${pageMaker.start - 1}&size=${pageMaker.size}">PREV</li>
    </c:if>

        <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
        <li class="${page == pageMaker.page?"current":""}"><a href="/board/list.do?page=${page}&size=${pageMaker.size}">${page}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li><a href="/board/list.do?page=${pageMaker.end + 1}&size=${pageMaker.size}">NEXT</a></li>
    </c:if>

</ul>

</body>
</html>
