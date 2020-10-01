/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Servlet;

import at.htlkaindorf.beans.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gottl
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {
    private List<Student> students = new ArrayList<>();
    private String filter = "";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        request.getRequestDispatcher("studentView.jsp").forward(request, response);
        
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String path = this.getServletContext().getRealPath("/res/students_2020.csv");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line = br.readLine();
            String lastClass = "";
            int katNr = 1;
            while((line = br.readLine()) != null){
                
                String[] tokens = line.split(";");
                LocalDate birthdate = LocalDate.parse(tokens[4], DTF);
                if(!lastClass.equals(tokens[0])){
                    katNr = 1;
                }
                students.add(new Student(tokens[1], tokens[2], tokens[0], katNr, birthdate, tokens[3].charAt(0)));
                lastClass = tokens[0];
                katNr++;
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("studentList");
        String nachname = name.split(" ")[0];
        Student student = null;
        for (Student student1 : students) {
            if(student1.getNachname().toUpperCase().equals(nachname)){
                student = student1;
            }
        }
        request.setAttribute("student", student);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        filter = request.getParameter("filter").toUpperCase();
        List<Student> filteredStudents = new ArrayList<>();
        if(!filter.equals("")){
            for (Student student : students){
                if(student.getNachname().contains(filter)){
                    filteredStudents.add(student);
                }
            }
        }else{
            filteredStudents = students;
        }
        request.setAttribute("students", filteredStudents);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
