<%-- 
    Document   : Menue
    Created on : 07.10.2020, 11:32:39
    Author     : Gottl
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <script src="validation.js" type="text/javascript"></script>
        <title>Menü</title>
    </head>
    <body>
        <div class="headline" >
            <img src="src/logo.jpg" class="logo">
            <h1 class="title">Pizzeria di Metro</h1>
            <div class="languageSelection">
                <label>Language</label>
                <select>
                    <option>Deutsch</option>
                    <option>English</option>
                </select>
            </div>
        </div>
        <form  method="POST" onsubmit="return validate()">
            <div class="pizzaList">
                <table class="menuTable">
                    <c:forEach var="pizza" items="${pizzas}">
                        <tr>
                            <td value="${pizza.base64}"><img src="${pizza.base64}" width="100px" height="100px"></td>
                            <td value="${pizza.name}">${pizza.name}</td>
                            <td value="${pizza.price}">Preis: ${pizza.price} €</td>
                            <td>
                                <select name="number_${pizza.name}" id="${pizza.name}">
                                    <c:forEach var="i" begin="0" end="10">
                                        <option  value="${i}">${i}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div>
                <label>Lieferadresse:</label>
                <input type="text" name="address" id="address" required="true">
                <input type="submit" name="bestellen" value="Bestellen">
            </div>
        </form>
    </body>
</html>
