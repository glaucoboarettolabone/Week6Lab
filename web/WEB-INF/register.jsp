<%-- 
    Document   : register
    Created on : Oct 13, 2020, 10:47:01 PM
    Author     : 815000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="post" action="ShoppingList">
            <b>Username: </b><input type="text" name="username">
            <input type="hidden" name="action" value="register"><br><br>
            <input type="submit" value="Register name">
        </form>
        <c:if test="${error == true}">
            <p>Username cannot be empty.</p>
        </c:if>
    </body>
</html>
