<%-- 
    Document   : Menue
    Created on : 07.10.2020, 11:32:39
    Author     : Gottl
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                <label>
                    <c:choose>
                        <c:when test="${sessionScope.lang=='EN'}">Language: </c:when>
                        <c:otherwise>Sprache: </c:otherwise>
                    </c:choose>
                </label>
                <form action="PizzaOrderServlet" method="POST" name="languageForm">
                    <input type="hidden" name="page" value="order">
                    <select onchange="submit()" name="languageSelect">
                        <c:choose>
                            <c:when test="${sessionScope.lang!=null}">
                                <option <c:if test="${sessionScope.lang=='DE' || sessionScope.lang==''}">selected</c:if> value="DE">Deutsch</option>
                                <option <c:if test="${sessionScope.lang=='EN'}">selected</c:if> value="EN">English</option>
                            </c:when>
                            <c:otherwise>
                                <option value="DE">Deutsch</option>
                                <option value="EN">English</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </form>
            </div>
        </div>
        <form  method="POST" onsubmit="return validate()" name="orderForm">
            <div class="pizzaList">
                <table class="menuTable">
                    <c:forEach var="pizza" items="${sessionScope.pizzas}">
                        <tr>
                            <td value="${pizza.base64}"><img src="${pizza.base64}" width="100px" height="100px"></td>
                            <td value="${pizza.name}">${pizza.name}</td>
                            <td value="${pizza.price}">
                                <c:choose>
                                    <c:when test="${sessionScope.lang=='EN'}">Price: </c:when>
                                    <c:otherwise>Preis: </c:otherwise>
                                </c:choose>
                                <fmt:setLocale value = "de_DE"/>
                                <fmt:formatNumber value = "${pizza.price}" type = "currency"/>
                            </td>
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
                <label>
                    <c:choose>
                        <c:when test="${sessionScope.lang=='EN'}">Delievery address: </c:when>
                        <c:otherwise>Lieferadresse: </c:otherwise>
                    </c:choose>
                </label>
                <input type="text" name="address" id="address" required="true">
                <input type="submit" name="bestellen" 
                       <c:choose>
                           <c:when test="${sessionScope.lang=='EN'}">value="Order"</c:when>
                           <c:otherwise>value="Bestellen"</c:otherwise>
                       </c:choose>>
            </div>
        </form>
    </body>
</html>
