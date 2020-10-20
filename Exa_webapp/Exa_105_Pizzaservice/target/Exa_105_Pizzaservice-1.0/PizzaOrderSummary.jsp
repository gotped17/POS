<%-- 
    Document   : Warenkorb
    Created on : 07.10.2020, 11:32:48
    Author     : Gottl
--%>

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
            <table>
                <tr style="text-align: center">
                    <td>Pizza</td>
                    <td>Preis</td>
                    <td>Stk</td>
                    <td>Gesamt</td>
                </tr>
                <%


                    Map<Pizza, Integer> order = (HashMap) session.getAttribute("order");
                    if (order != null) {
                        double sum = 0;
                        for (Pizza p : order.keySet()) {
                            if (order.get(p) > 0) {
                                out.write("<tr>");
                                out.write(String.format("<td>%s</td>", p.getName()));
                                out.write(String.format("<td>%4.2f</td>", p.getPreis()));
                                out.write(String.format("<td>%d</td>", order.get(p)));
                                out.write(String.format("<td>%.2f</td>", p.getPreis() * order.get(p)));
                                out.write("</tr>");
                                sum += p.getPreis() * order.get(p);
                            }
                        }
                        out.write("<tr>");
                        out.write("<td></td>");
                        out.write("<td></td>");
                        out.write("<td>Summe:</td>");
                        out.write(String.format("<td>%.2f</td", sum));
                        out.write("</tr>");
                        out.write("</table>");

                    }
                %>

        </div>
        <div>
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
