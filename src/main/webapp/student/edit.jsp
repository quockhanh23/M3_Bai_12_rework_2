<%--
  Created by IntelliJ IDEA.
  User: Asus VivoBook
  Date: 12/2/2021
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>alo</h2>

<form method="post">
    <input type="number" name="id" value="${edit.id}">
    <input type="text" name="name" value="${edit.name}">
    <input type="number" name="age" value="${edit.age}">
    <input type="text" name="address" value="${edit.address}">
    <button>Edit</button>
</form>
</body>
</html>
