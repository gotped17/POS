/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.Controller;

import at.htlkaindorf.bl.ContactListModel;
import at.htlkaindorf.json.JSON_access;
import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
@WebServlet(name = "ContactController", urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {
    private JSON_access json;
    private ContactListModel clm;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        Path path = Paths.get(config.getServletContext().getRealPath("/src/contacts.json"));
        json = new JSON_access(path);
        clm = new ContactListModel(json);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] genderArr = {"Female", "Male"};
        request.getSession().setAttribute("genders", Arrays.asList(genderArr));
        request.getSession().setAttribute("model", clm);
        request.getRequestDispatcher("ContactView.jsp").forward(request, response);
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
        ContactListModel clm = (ContactListModel) request.getSession().getAttribute("model");
        request.setAttribute("message", "");
        if (clm == null) {
            processRequest(request, response);
            return;
        }
        Map<String, String[]> parameter = request.getParameterMap();

        if (parameter.get("filterSubmit") != null) {
            String company = request.getParameter("companyFilter");
            String gender = request.getParameter("genderFilter");
            String name = request.getParameter("nameFilter");
            clm.filter(company, name, gender);
            request.setAttribute("company", company);
            request.setAttribute("selectedGender", gender);
            request.setAttribute("name", name);
            processRequest(request, response);
            return;
        }
        if (parameter.get("delButton") != null) {
            List<Contact> contacts = clm.getContacts();
            List<Contact> dueToRemove = new ArrayList<>();
            for (Contact contact : contacts) {
                if (request.getParameter("cb_" + contact.getId()) != null) {
                    dueToRemove.add(contact);
                }
            }
            for (Contact con : dueToRemove) {
                clm.removeContact(con);
            }
            request.setAttribute("message", "Successfully deleted " + dueToRemove.size() + " contacts");
            processRequest(request, response);
            return;
        }

        if (parameter.get("favButton") != null) {
            List<Contact> contacts = clm.getContacts();
            for (Contact contact : contacts) {
                if (request.getParameter("cb_" + contact.getId()) != null) {
                    clm.addFavourite(contact.getId());
                }
            }

            request.setAttribute("message", "Successfully added contacts to favourites");
            processRequest(request, response);
            return;
        }

        if (parameter.get("exportButton") != null) {
            PrintWriter out = response.getWriter();
            String json = "";
            try {
                json = clm.exportFavourites();
            } catch (JsonProcessingException e) {
                json = "[{error: \"Error while exporting\"}]";
            }

            response.setContentType("application/json");
            response.setHeader("Content-Disposition", "attachment; filename=\"favourites.json\"");
            out.write(json);
            return;
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
