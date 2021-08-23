<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2021-08-19
  Time: 오후 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form ACTION="/board/register.do" method="post">
    <input type="text" name="title" value="샘플제목">
    <textarea name="content"></textarea>
    <input type="text" name="writer" value="user1">
    <button type="submit">등록</button>
</form>
</body>
</html>
