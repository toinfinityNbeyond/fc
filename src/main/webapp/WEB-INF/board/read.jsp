<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-19
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>${boardDTO.title}</div>
<div>${boardDTO.content}</div>
<div>${boardDTO.writer}</div>
<div>${boardDTO.regdate}</div>
<div>${pageDTO}</div>   <!--값 잘 나왔는지.택배 확인-->

<a href="/board/list.do?page=${pageDTO.page}&size=${pageDTO.size}">목록가기</a>
<form action="/board/remove.do?bno=${boardDTO.bno}" method="post">
    <button type="submit">삭제</button>
</form>

<a href="/board/modify.do">수정</a>

<%--<button type="submit">수정</button>--%>


</body>
</html>
