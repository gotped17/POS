<%-- 
    Document   : studentView
    Created on : 28.09.2020, 08:35:30
    Author     : Gottl
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="at.htlkaindorf.beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/css/desgin.css"></style>
    <title>JSP Page</title>
</head>
<body style="align: center; background-color: #dadada;">
    <h1>Schülerliste</h1>
    <div style="text-align: left;background-color: white;border-radius: 20pt;padding-left: 35%">
        <form action="StudentController" method="POST">
            <div style="display: inline;" class="formStyle">
                <label>Filter:</label>
                <input type="text" name="filter">
                <input type="submit" name="useFilter" value="Filter anwenden">
                <input type="submit" name="removeFilter" value="Filter löschen">
            </div>
        </form>


        <br>
        <form action="StudentController" method="GET">
            <div style="display: inline;">
                <label>Schüler auswählen</label>
                <select name="studentList" onchange="submit()">
                    <%
                        List<Student> students = (ArrayList) request.getAttribute("students");
                        if (students != null) {
                            System.out.println(students.size());
                            for (Student student : students) {
                                out.write("<option>" + student.getNachname().toUpperCase() + " " + student.getVorname() + "</option>\n");
                            }
                        }
                    %>
                </select>
            </div>
        </form>


    </div>
    <div class="studentCard" style="background-color: yellowgreen;
         border-radius: 20pt;">
        <%
            Student student = (Student) request.getAttribute("student");
        %>
    </div>

</div>


</body>
</html>
