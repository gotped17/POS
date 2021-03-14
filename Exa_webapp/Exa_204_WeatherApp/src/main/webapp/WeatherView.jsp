<%-- 
    Document   : WeatherView
    Created on : 17.02.2021, 07:09:28
    Author     : Gottl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weather.io</title>
        <link href="app.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <div class="container">
            <div class="header">
                <form action="OWMController" method="POST" name="languageSelector">
                    <label>
                        <c:choose>
                            <c:when test="${sessionScope.lang == 'EN'}">
                                Language
                            </c:when>
                            <c:otherwise>
                                Sprache
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <select name="languageSelect" onchange="submit()">
                        <option>DE</option>
                        <option <c:if test="${sessionScope.lang == 'EN'}">selected</c:if>>EN</option>
                        </select>
                    </form>
                </div>
                <div class="searchbar">
                    <form method="POST" action="OWMController" name="searchForm">
                        <input type="search" placeholder="Type city name" name="citySearch" onchange="submit()">
                    </form>
                <c:if test="${owmError}">
                    <c:choose>
                        <c:when test="${sessionScope.lang == 'EN'}">
                            Error loading entered city
                        </c:when>
                        <c:otherwise>
                            Fehler beim laden der eingegebenen Stadt
                        </c:otherwise>
                    </c:choose>    
                </c:if>
            </div>
            <div class="weatherContainer">
                <table>
                    <tr>
                        <td>
                            ${weather.getCity().getName()} (${weather.getCity().getCountry()})
                        </td>
                    </tr>
                    <tr>
                        <td>
                            UTC<c:if test="${weather.getCity().getTimezone() / 3600 >=0}">+</c:if>${weather.getCity().getTimezone() / 3600}
                            </td>
                        </tr>
                        <tr>
                            <td>
                            ${weather.getTemperature().getValue()} ${weather.getTemperature().getUnit()}  
                        </td>
                        <td>
                            (${weather.getTemperature().getMin()} - ${weather.getTemperature().getMax()})
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.lang == 'EN'}">
                                    humidity
                                </c:when>
                                <c:otherwise>
                                    Luftfeuchtigkeit
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${weather.getHumidity().getValue()} ${weather.getHumidity().getUnit()}</td>
                    </tr>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.lang == 'EN'}">
                                    Pressure
                                </c:when>
                                <c:otherwise>
                                    Luftdruck
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${weather.getAirPressure().getValue()} ${weather.getAirPressure().getUnit()}</td>
                    </tr>
                    <tr>
                        <td>
                            Wind
                        </td>
                        <td>
                            ${weather.getWind().getSpeed().getValue()} ${weather.getWind().getSpeed().getUnit()} ${weather.getWind().getDirection().getName()}
                        </td>
                    </tr>
                </table>
                ${weather.getWeather().getValue()}
            </div>
        </div>
    </center>
    </body>
</html>
