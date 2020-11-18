/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Controller;

import at.htlkaindorf.BL.Supplierplan;
import at.htlkaindorf.beans.Stunde;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gottl
 */
@WebServlet(name = "SupplierplanController", urlPatterns = {"/SupplierplanController"})
public class SupplierplanController extends HttpServlet {

    private Supplierplan supplierplan;
    private List<String> tageKurz = new ArrayList<>();
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
        request.getRequestDispatcher("SupplierplanView.jsp").forward(request, response);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Path path = Paths.get(this.getServletContext().getRealPath("/src/stundenplan.csv"));
        tageKurz.add("Mo");
        tageKurz.add("Di");
        tageKurz.add("Mi");
        tageKurz.add("Do");
        tageKurz.add("Fr");
        
        supplierplan = new Supplierplan(path);
        // System.out.println(supplierplan.getStundenplan().size());
        config.getServletContext().setAttribute("klasse", supplierplan.getKlasse());
        config.getServletContext().setAttribute("supplierplan", supplierplan);
        config.getServletContext().setAttribute("tage", tageKurz);
        
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
        
        String tag = request.getParameter("tagauswahl");
        int stunde = Integer.parseInt(request.getParameter("stunde"));
        String fach = request.getParameter("fach");
        String lehrerString = request.getParameter("lehrer");
        
        List<String> lehrer = new ArrayList<>();
        String[] lehrerFeld = lehrerString.split(",");
        for (String l : lehrerFeld) {
            lehrer.add(l);
        }
        supplierplan.addSupplierung(tag, stunde, fach, lehrer);
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
