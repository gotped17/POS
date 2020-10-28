<%-- 
    Document   : Warenkorb
    Created on : 07.10.2020, 11:32:48
    Author     : Gottl
--%>
<%@page import="at.htlkaindorf.beans.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
        <title>Bestellung</title>
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
                    <input type="hidden" name="page" value="summary">
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

        <div id="summary">
            <table class="orderTable">
                <tr style="text-align: center;font-size: 18pt">
                    <th class="orderHeader">Pizza</th>
                    <th class="orderHeader">
                        <c:choose>
                            <c:when test="${sessionScope.lang=='EN'}">Price</c:when>
                            <c:otherwise>Preis</c:otherwise>
                        </c:choose>
                    </th>
                    <th class="orderHeader">
                        <c:choose>
                            <c:when test="${sessionScope.lang=='EN'}">QTY</c:when>
                            <c:otherwise>Stk</c:otherwise>
                        </c:choose>
                    </th>
                    <th class="orderHeader">
                        <c:choose>
                            <c:when test="${sessionScope.lang=='EN'}">Total</c:when>
                            <c:otherwise>Gesamt</c:otherwise>
                        </c:choose>
                    </th>
                </tr>
                
                <c:set var="sum" value="${0}"/>
                <c:forEach var="orderedPizza" items="${sessionScope.order}">
                    <c:if test="${orderedPizza.count > 0}">

                        <tr>
                            <td class="orderCell" value="${orderedPizza.pizza.name}">${orderedPizza.pizza.name}</td>

                            <td class="orderCell" value="${orderedPizza.pizza.price}">
                                <fmt:setLocale value = "de_DE"/>
                                <fmt:formatNumber value = "${orderedPizza.pizza.price}" type = "currency"/></td>
                            <td class="orderCell" value="${orderedPizza.count}">${orderedPizza.count}</td>
                            <td class="orderCell" value="${orderedPizza.pizza.price*orderedPizza.count}">
                                <fmt:setLocale value = "de_DE"/>
                                <fmt:formatNumber value = "${orderedPizza.pizza.price*orderedPizza.count}" type = "currency"/>
                            </td>
                        </tr>
                        <c:set var="sum" value="${sum + orderedPizza.pizza.price*orderedPizza.count}"/>
                    </c:if>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td class="sumCell"><c:choose>
                            <c:when test="${sessionScope.lang=='EN'}">Sum: </c:when>
                            <c:otherwise>Summe: </c:otherwise>
                        </c:choose></td>
                    <td class="sumCell" value="${sum}">
                        <fmt:setLocale value = "de_DE"/>
                        <fmt:formatNumber value = "${sum}" type = "currency"/></td>
                </tr>
            </table>
        </div>
        <div style="padding:25px 0px 10px 0px">

            <c:choose>
                <c:when test="${sessionScope.lang=='EN'}">Delievery address: </c:when>
                <c:otherwise>Lieferadresse: </c:otherwise>
            </c:choose>
            ${sessionScope.address}
        </div>
        <div>
            <form action="PizzaOrderServlet" method="POST">
                <input type="submit" name="back" <c:choose>
                           <c:when test="${sessionScope.lang=='EN'}">value="Back"</c:when>
                           <c:otherwise>value="Zurück"</c:otherwise>
                       </c:choose>>
            </form>
        </div>
    </body>
</html>
