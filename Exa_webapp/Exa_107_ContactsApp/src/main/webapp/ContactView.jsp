<%-- 
    Document   : ContactView
    Created on : 01.12.2020, 09:28:17
    Author     : Gottl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="src/app.css" rel="stylesheet" type="text/css"/>
        <script src="src/vertTabs.js" type="text/javascript"></script>
        <title>ContactApp</title>
    </head>
    <body>
    <center>
        <h1>Contacts</h1>
        <form method="POST" action="ContactController">
            <div class="filterBlock">

                <h3>Filter </h3>
                <label>Company: </label>
                <select name="companyFilter">
                    <option>select</option>
                    <c:forEach var="company_obj" items="${model.getCompanies()}">
                        <option <c:if test="${company.equals(company_obj.toString())}">selected</c:if>>
                            ${company_obj.name}(${company_obj.stockmarket})
                        </option>
                    </c:forEach>
                </select>
                <label>Gender: </label>
                <select name="genderFilter">
                    <option>select</option>
                    <c:forEach var="gender" items="${genders}">
                        <option <c:if test="${gender.equals(selectedGender)}">selected</c:if>>
                            ${gender}
                        </option>
                    </c:forEach>
                </select>
                <label>Name: </label>
                <input type="text"  name="nameFilter" value="${name}">
                <input type="submit" name="filterSubmit" value="Filter list">
            </div>
            <div class="buttonBlock">
                <div class="inlineContent" method="POST" action="ContactController">
                    <input type="submit" name="delButton" value="Delete Selected Contacts">
                    <input type="submit" name="favButton" value="Add Favourites">
                    <input type="submit" name="exportButton" value="Export Favourites">
                </div>
            </div>
            <div class="messageBlock">
                <p class="message">${message}</p>
                <p class="error">${error}</p>
            </div>
            <div class="contentBlock">
                <table>
                    <tr class="tableHead">
                        <td>

                        </td>
                        <td>
                            Name
                        </td>
                        <td>
                            Working for
                        </td>
                        <td>
                            E-Mail
                        </td>
                        <td>
                            Date of birth
                        </td>
                    </tr>
                    <c:forEach var="contact" items="${model.getContacts()}">
                        <tr>
                            <td>
                                <input type="checkbox" name="cb_${contact.id}" value="marked">
                            </td>
                            <td>
                                ${contact.firstname} ${contact.lastname}(${contact.gender.charAt(0)})
                            </td>
                            <td>
                                ${contact.company.getName()}(${contact.company.stockmarket})
                            </td>
                            <td>
                                ${contact.getEmailString()}
                            </td>
                            <td>
                                ${contact.getDateFormatted()}
                            </td>
                        </tr>

                    </c:forEach>
                </table>

            </div>
        </form>
    </center>

</body>
</html>
