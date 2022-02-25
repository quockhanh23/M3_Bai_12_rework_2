<%--
  Created by IntelliJ IDEA.
  User: Asus VivoBook
  Date: 12/2/2021
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>

    </style>
</head>
<body>
<h2>list</h2>
<a href="/students?action=create">create new</a>
<c:forEach var="cus" items="${alo}">
    <h2>${cus.id}, ${cus.name}, ${cus.age}, ${cus.address}
        <a href="/students?action=edit&id=${cus.id}">Edit</a>
        <a href="/students?action=delete&id=${cus.id}">Delete</a>
    </h2>
</c:forEach>
</body>
</html>
