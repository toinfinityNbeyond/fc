<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-19
  Time: 오후 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/board/modify.do" method="post">
    <input type="text" name="bno" value="bno">
    <input type="text" name="title" value="title">
    <input type="text" name="content" value="content">
    <button type="submit">MODIFY</button>
</form>
</body>
</html>
