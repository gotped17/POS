<%-- 
    Document   : Menue
    Created on : 07.10.2020, 11:32:39
    Author     : Gottl
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="at.htlkaindorf.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet">
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
        <form action="PizzaOrderServlet" method="POST">
            <div class="pizzaList">
                <table>
                    <%
                        List<Pizza> pizzas = (ArrayList) request.getAttribute("pizzas");
                        if (pizzas != null) {
                            for (Pizza pizza : pizzas) {
                                out.write("<tr>");
                                out.write("<td>" + pizza.getName() + "</td>");
                                out.write(String.format("<td><img src=\"%s\"></td>", pizza.getBase64()));
                                out.write(String.format("<td>Preis: %5.2f €</td>", pizza.getPreis()));
                                out.write(String.format("<td><select name=\"number_%s\">", pizza.getName()));
                                for (int i = 0; i <= 10; i++) {
                                    out.write(String.format("<option>%d</option>", i));
                                }

                                out.write("</td></tr>");

                            }
                        }
                    %>
                </table>

            </div>
            <div>
                <label>Lieferadresse:</label>
                <input type="text" name="address">
                <input type="submit" name="bestellen" value="Bestellen"></div>

        </form>

    </body>
</html>
