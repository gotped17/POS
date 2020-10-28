/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Controller;

import at.htlkaindorf.beans.Pizza;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

/**
 *
 * @author Gottl
 */
@WebServlet(name = "PizzaOrderServlet", urlPatterns = {"/PizzaOrderServlet"})
public class PizzaOrderServlet extends HttpServlet {

    private List<Pizza> pizzas = new ArrayList<>();

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
        request.setAttribute("pizzas", pizzas);
        request.getRequestDispatcher("PizzaOrder.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        try {
            super.init(); //To change body of generated methods, choose Tools | Templates.
            List<String> pizzaStrings = Files.lines(Paths.get(this.getServletContext().getRealPath("/src/pizzas.csv")))
                    .skip(1)
                    .map(String::new)
                    .collect(Collectors.toList());
            for (String pizzaString : pizzaStrings) {
                String[] tokens = pizzaString.split(";");
                pizzas.add(new Pizza(tokens[0], Double.parseDouble(tokens[1].replace(',', '.')), tokens[2] + ";" + tokens[3]));
            }

        } catch (IOException ex) {
            Logger.getLogger(PizzaOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        Map<Pizza, Integer> orderedPizzas = new HashMap<>();
        if (request.getParameter("back") != null) {
            processRequest(request, response);
        }

        for (Pizza pizza : pizzas) {
            System.out.println(pizza.getName());
            String value = request.getParameter(String.format("number_%s", pizza.getName()));
            System.out.println(value);
            int count = Integer.parseInt(value);
            orderedPizzas.put(pizza, count);
        }
        String address = request.getParameter("address");
        request.getSession().setAttribute("address", address);
        request.getSession().setAttribute("order", orderedPizzas);
        request.getRequestDispatcher("PizzaOrderSummary.jsp").forward(request, response);

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
