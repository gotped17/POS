/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Controller;

import at.htlkaindorf.beans.Stunde;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

/**
 *
 * @author Gottl
 */
@WebServlet(name = "SupplierplanController", urlPatterns = {"/SupplierplanController"})
public class SupplierplanController extends HttpServlet {
    private String klasse;
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
        
    }

    @Override
    public void init() throws ServletException {
        try {
            super.init();
            List<Stunde> timetableRaw = new ArrayList<>();
            klasse = Files.lines(Paths.get(this.getServletContext().getRealPath("/src/stundenplan.csv"))).limit(1).toString();
            timetableRaw = Files.lines(Paths.get(this.getServletContext().getRealPath("/src/stundenplan.csv")))
                    .skip(1)
                    .map(Stunde::new)
                    .collect(Collectors.toList());
            Map<Integer, List<Stunde>> timetable = new HashMap<>();
            int dayCount = 1;
            int lessonCount = 1;
            List<Stunde> lessonStunde = new ArrayList<>();
            for (Stunde stunde : timetableRaw) {
                lessonStunde.add(stunde);
                dayCount++;
                if(dayCount == 5){
                    dayCount = 1;
                    timetable.put(lessonCount, lessonStunde);
                    lessonCount++;
                    lessonStunde.clear();
                }
            }
            
                
            } catch (IOException ex) {
            Logger.getLogger(SupplierplanController.class.getName()).log(Level.SEVERE, null, ex);
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
