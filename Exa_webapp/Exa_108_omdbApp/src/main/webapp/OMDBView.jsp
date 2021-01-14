<%-- 
    Document   : OMDBView
    Created on : 22.12.2020, 10:06:21
    Author     : Gottl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OMDB app</title>
        <link href="app.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <h1>OMDB App</h1>
        <div class="menubar">
            <form method="POST" action="OMDBController" name="searchForm">
                <input type="text" placeholder="search" name="movieName">
                <input type="submit" value="&#128269;" name="nameSubmit">
            </form>
            
            <form method="POST" action="OMDBController" name="genreFilter">
                <label>Genre: </label>
                <select name="filterGenre" onchange="submit()">
                    <option>All</option>
                    <c:forEach var="genre" items="${model.getGenres()}">
                        <option <c:if test="${curGenre == genre}">selected</c:if>>${genre}</option>
                    </c:forEach>
                </select>
            </form>
            <form method="POST" action="OMDBController" name="listSort">
                <label>Order by: </label>
                <select name="sortSelect" onchange="submit()">
                    <option>None</option>
                    <option>Title</option>
                    <option>Release</option>
                </select> 
            </form>
        </div>
        <div class="moviedisplay">
            <c:set var="count" value="${0}"></c:set>
                <table>
                    <tr>
                    <c:forEach var="movie" items="${model.getMovies()}">
                        <td>
                            <div class="card">
                                <img src="${movie.getPoster()}" alt="Poster" style="width:100%">
                                <div class="container">
                                    <h4><b>${movie.getTitle()}</b></h4>
                                    <p>${movie.getReleaseRaw()}</p>
                                    <p>Rating: ${movie.getRating()}</p>
                                </div>
                            </div>  
                        </td>
                        <c:set var="count" value="${count + 1}"></c:set>
                        <c:if test="${count == 5 || count == 10}">
                        </tr>
                        <tr>
                        </c:if>
                    </c:forEach>
            </table>
        </div>
        <div class="footerBlock">
            <form method="POST" action="OMDBController" name="paginationForm">
                <button onclick="submit()" name="prev" <c:if test="${model.getCurrentPage() == 1}">disabled="true"</c:if>> < </button>
                Page ${model.getCurrentPage()}
                <button onclick="submit()" name="next"> > </button> 
            </form>
            <br>
            ${model.getNumberOfResults()} total Results

        </div>
    </center>
</body>
</html>
