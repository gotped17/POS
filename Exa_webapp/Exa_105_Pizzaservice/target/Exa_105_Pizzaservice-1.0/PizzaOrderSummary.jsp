<%-- 
    Document   : Warenkorb
    Created on : 07.10.2020, 11:32:48
    Author     : Gottl
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <c:when test="${cookie.cLang!=null}">
                                <option <c:if test="${cookie.cLang.value=='DE'}">selected</c:if> value="DE">Deutsch</option>
                                <option <c:if test="${cookie.cLang.value=='EN'}">selected</c:if> value="EN">English</option>
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
                <%
                    Map<Pizza, Integer> order = (HashMap) session.getAttribute("order");
                    if (order != null) {
                        double sum = 0;
                        for (Pizza p : order.keySet()) {
                            if (order.get(p) > 0) {
                                out.write("<tr>");
                                out.write(String.format("<td class=\"orderCell\">%s</td>", p.getName()));
                                out.write(String.format("<td class=\"orderCell\">%4.2f €</td>", p.getPrice()));
                                out.write(String.format("<td class=\"orderCell\">%d</td>", order.get(p)));
                                out.write(String.format("<td class=\"orderCell\">%.2f €</td>", p.getPrice() * order.get(p)));
                                out.write("</tr>");
                                sum += p.getPrice() * order.get(p);
                            }
                        }
                        out.write("<tr>");
                        out.write("<td ></td>");
                        out.write("<td></td>");
                        if (session.getAttribute("lang").equals("EN")) {
                            out.write("<td class=\"sumCell\">Sum:</td>");
                        } else {
                            out.write("<td class=\"sumCell\">Summe:</td>");
                        }
                        out.write(String.format("<td class=\"sumCell\">%.2f €</td", sum));
                        out.write("</tr>");
                        out.write("</table>");

                    }
                %>

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
