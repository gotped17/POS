/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Controller;

import at.htlkaindorf.pojo.CurrentWeather;
import at.htlkaindorf.xml.XMLAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Gottl
 */
@WebServlet(name = "OWMController", urlPatterns = {"/OWMController"})
public class OWMController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private CurrentWeather weather;
    private XMLAccess xmla;
    private String currentLanguage;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            xmla = XMLAccess.getInstance();
            weather = xmla.searchForCity("Graz");
        } catch (JAXBException ex) {
            ex.printStackTrace();
            System.out.println("Error with jaxb when gathering or initializing");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            System.out.println("Malformed url when receiving default");
        }
        config.getServletContext().setAttribute("weather", weather);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("WeatherView.jsp").forward(request, response);
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
        try {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("cLang")) {
                    request.getSession().setAttribute("lang", c.getValue());
                    currentLanguage = c.getValue();
                }
            }

        } catch (NullPointerException ex) {
            request.getSession().setAttribute("lang", "EN");
            currentLanguage = "EN";
            
        }
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
        if (request.getParameter("languageSelect") != null) {

            Cookie cLang = new Cookie("cLang", request.getParameter("languageSelect"));
            cLang.setMaxAge(30 * 24 * 3600);
            response.addCookie(cLang);
            request.getSession().setAttribute("lang", cLang.getValue());
            currentLanguage = cLang.getValue();
            
            //Returning the current city in selected language
            String searchString = ((CurrentWeather) request.getServletContext().getAttribute("weather")).getCity().getName();
            if(currentLanguage.equals("DE")){
                    searchString += "&lang=de";
                }
            try {
                request.getServletContext().setAttribute("weather", xmla.searchForCity(searchString));
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                request.setAttribute("owmError", true); 
            } catch (DataBindingException ex){
                request.setAttribute("owmError", true); 
            }
        }
        if(request.getParameter("citySearch")!= null){
            try {
                String searchString = request.getParameter("citySearch");
                System.out.println(currentLanguage);
                if(currentLanguage.equals("DE")){
                    searchString += "&lang=de";
                }
                request.getServletContext().setAttribute("weather", xmla.searchForCity(searchString));
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (JAXBException ex) {
                request.setAttribute("owmError", true); 
            } catch (DataBindingException ex){
                request.setAttribute("owmError", true); 
            }
            
        }
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
