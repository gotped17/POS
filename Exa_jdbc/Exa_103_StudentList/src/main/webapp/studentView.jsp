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
    <h1 style="text-align: center">Schülerliste</h1>
    <div style="text-align: left;background-color: white;border-radius: 20pt;margin-left: 32%;width: 500px; padding: 10px; margin-bottom: 15px">
        <form action="StudentController" method="POST">
            <div style="display: inline;" class="formStyle">
                <label>Filter:</label>
                <input type="text" name="filter" value="<% out.write((String) request.getAttribute("filter"));%>">
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
                        Student selected = (Student) request.getAttribute("selected");
                        if (students != null) {
                            System.out.println(students.size());
                            for (Student student : students) {
                                if (selected != null && selected.getFullName().equals(student.getFullName())) {
                                    out.write("<option selected>" + selected.getFullName() + "</option>");
                                } else {
                                    out.write("<option>" + student.getNachname().toUpperCase() + " " + student.getVorname() + "</option>\n");
                                }
                            }
                        }
                    %>
                </select>
            </div>
        </form>
    </div>
    <div class="studentCard" style="background-color: yellowgreen;
         border-radius: 20pt;width: 400px;margin-left: 35%; padding: 10px">
        <table> 

            <%            Student student = (Student) request.getAttribute("selected");
                if(student

            
                    != null){
                    out.println(String.format("<tr><td>Name: </td><td> %s %s</td></tr>", student.getNachname(), student.getVorname()));
                    out.println(String.format("<tr><td>Katalognummer: </td><td> %d</td></tr>", student.getKatNr()));
                    out.println(String.format("<tr><td>Klasse: </td><td> %s</td></tr>", student.getKlasse()));
                    out.println(String.format("<tr><td>Geschlecht: </td><td> %c</td></tr>", student.getGeschlecht()));
                    out.println(String.format("<tr><td>Geburtsdatum: </td><td> %s</td></tr>", student.getBirthdate().toString()));
                }
            %>
        </table>
    </div>

</div>


</body>
</html>
