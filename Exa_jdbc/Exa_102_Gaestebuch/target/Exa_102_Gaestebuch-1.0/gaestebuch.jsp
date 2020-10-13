<%-- 
    Document   : gaestebuch
    Created on : 24.09.2020, 08:42:39
    Author     : Gottl
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="at.htlkaindorf.beans.GuestBookEntry"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="validation.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Willkommen im Gästebuch</h1>
        <form action="GuestbookController" method="POST" onsubmit="validate()">
            <table border="0">
                <thead>
                    <tr>
                        <td>Nickname</td>
                        <td><input type="text" name="nickname" value="spiderman" id="nickname"/></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>E-Mail</td>
                        <td><input type="text" name="email" value="spiderman@marvel.com" id="email"></td>
                    </tr>
                    <tr>
                        <td>Comment</td>
                        <td><textarea name="comment" rows="4" cols="25" id="comment">
                            guestbook
                            </textarea></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="send" value="send"></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
        <hr>
        <br>
        <%
            List<GuestBookEntry> entries = (ArrayList) request.getAttribute("entries");
            if(entries != null){
                for (GuestBookEntry entry : entries) {
                    out.println(entry.toString() + "<br>");
                }    
            }
            
        %>
    </body>
</html>
