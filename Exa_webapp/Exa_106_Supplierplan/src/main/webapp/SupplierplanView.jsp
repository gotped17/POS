<%-- 
    Document   : SupplierplanView
    Created on : 04.11.2020, 11:40:37
    Author     : Gottl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="at.htlkaindorf.beans.Stunde"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="src/validation.js" type="text/javascript"></script>
        <link href="src/styles.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplierplan</title>
    </head>
    <body>
        <header class="headline">
            <h1>Supplierplan</h1>
            <h2>${klasse}</h2>
        </header>
        <div>
            <form action="SupplierplanController" method="POST" onsubmit="return validate();" name="change">
                <table class="supplierForm">

                    <tr>
                        <td><label>Tag:</label></td>
                        <td>
                            <select name="tagauswahl">
                                <option>Montag</option>
                                <option>Dienstag</option>
                                <option>Mittwoch</option>
                                <option>Donnerstag</option>
                                <option>Freitag</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Stunde:</label></td>
                        <td><select name="stunde">
                                <c:forEach var="i" begin="1" end="10">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Fach:</label>
                        </td>
                        <td>
                            <input type="text" id="Fach" name="fach">
                        </td>
                        <td><p class="error" id="fachError"></p></td>
                    </tr>
                    <tr>
                        <td>
                            <label>Lehrer:</label>
                        </td>
                        <td>
                            <input type="text" id="Lehrer" name="lehrer">
                        </td>
                        <td><p class="error" id="lehrerError"></p></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Übernehmen">
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>

                </table>
            </form>
        </div>

        <div>
            <table class="stundenplan">
                <thead>
                    <th></th>
                    <c:forEach items="${tage}" var="tag">
                    <th value="${tag}">${tag}</th>
                    </c:forEach>
                </thead>
                <tbody>
                    <c:forEach begin="1" end="10" var="i">
                        <tr>
                            <td>${i}</td>
                            <c:forEach items="${tage}" var="tag">
                                
                                <c:set var="stunde" value="${supplierplan.getStunde(tag, i)}" />
                                <td class="${stunde.isSuppliert() ? 'fachSuppliert' : ''}">
                                    <div class="stundenItem">
                                        <b>${stunde.fach}</b>
                                        <p>${stunde.getLehrerString()}</p>
                                    </div>
                                    
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
