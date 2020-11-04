<%-- 
    Document   : eltslintro
    Created on : 21.10.2020, 10:56:42
    Author     : Gottl
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c%taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <select>
            <c:forEach var="i" begin="1" end="10">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        
    </body>
</html>
