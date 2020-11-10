<%-- 
    Document   : SupplierplanView
    Created on : 04.11.2020, 11:40:37
    Author     : Gottl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h2 value="${klasse}">${klasse}</h2>
        </header>
        <div class="supplierForm">
            <form action="SupplierplanController" method="POST" onsubmit="return validate()">
                <table border="1">

                    <tr>
                        <td><label>Tag:</label></td>
                        <td>
                            <select name="Tagauswahl">
                                <c:forEach var="tag" items="${tage}">
                                    <option value="${tag}">${tag}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Stunde:</label></td>
                        <td><select name="Stunde">
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
                            <input type="text" id="Fach" name="Fach">
                        </td>
                        <td><p class="error" id="fachError"></p></td>
                    </tr>
                    <tr>
                        <td>
                            <label>Lehrer:</label>
                        </td>
                        <td>
                            <input type="text" id="Lehrer" name="Lehrer">
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

        <div class="timetable">
            <table>
                <thead>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </thead>
                <c:forEach var="stunde" items="timetable">
                    
                </c:forEach>
            </table>
        </div>
    </body>
</html>
