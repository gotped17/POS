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
                <label>Language</label>
                <select>
                    <option>Deutsch</option>
                    <option>English</option>
                </select>
            </div>
        </div>

        <div id="summary">
            <table class="orderTable">
                <tr style="text-align: center;font-size: 18pt">
                    <th class="orderHeader">Pizza</th>
                    <th class="orderHeader">Preis</th>
                    <th class="orderHeader">Stk</th>
                    <th class="orderHeader">Gesamt</th>
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
                        out.write("<td class=\"sumCell\">Summe:</td>");
                        out.write(String.format("<td class=\"sumCell\">%.2f €</td", sum));
                        out.write("</tr>");
                        out.write("</table>");

                    }
                %>
                <c:forEach var="orderedPizza" items="${sessionScope.order}">
                    <tr>
                        <td class="orderCell" value="${orderedPizza.key}">${orderedPizza.key.name}</td>
                        <td class="orderCell" value="${orderedPizza.key}">${orderedPizza.price} €</td>
                        <td class="orderCell" value="${sessionScope.order[orderedPizza.key]}">${sessionScope.order[orderedPizza.key]}</td>
                        <td class="orderCell" value="${orderedPizza.key.price*order[orderedPizza.key]}">${orderedPizza.key.price*sessionScope.order[orderedPizza.key]} €</td>
                    </tr>
                    
                </c:forEach>
            </table>
        </div>
        <div style="padding:25px 0px 10px 0px">
            <%
                String address = (String) session.getAttribute("address");
                if(address != null)
                    out.write(String.format("Lieferadresse: %s", address));
            %>
        </div>
        <div>
            <form action="PizzaOrderServlet" method="POST">
                <input type="submit" name="back" value="zurück">
            </form>
        </div>
    </body>
</html>
