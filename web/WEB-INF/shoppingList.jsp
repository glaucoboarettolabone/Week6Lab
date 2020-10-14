<%-- 
    Document   : shoppingList
    Created on : Oct 13, 2020, 10:47:14 PM
    Author     : 815000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}</p>
        <a href="<c:url value='ShoppingList?action=logout' />">
            Logout
        </a>
        <h2>List</h2>
        <form method="post" action="">
            <b>Add item </b><input type="text" name="item"> <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <c:if test="${error == true}">
            <p>Item cannot be empty.</p>
        </c:if>
    </body>
</html>
