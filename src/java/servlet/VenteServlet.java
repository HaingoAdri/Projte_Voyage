/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connexion.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;
import model.Genre;
import model.Vente;
import model.Voyage;

/**
 *
 * @author Ndimby Razafinjatovo
 */
@WebServlet(name = "VenteServlet", urlPatterns = {"/VenteServlet"})
public class VenteServlet extends HttpServlet {
    DbConnection c  = new DbConnection();
    Voyage v = new Voyage();
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
            throws ServletException, IOException, Exception {
        Vector<Genre> liste  = Genre.liste_Genre(c.connectToPostgres());
        Vector<Voyage> voyage = v.listeVoyage(c.connectToPostgres());
        List<Vente> vente = Vente.getStatistique(c.connectToPostgres());
        request.setAttribute("genre", liste);
        request.setAttribute("voyage", voyage);
        request.setAttribute("vente", vente);
        RequestDispatcher dispacth = request.getRequestDispatcher("vente.jsp");
        dispacth.forward(request, response);
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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Date d = Date.valueOf(request.getParameter("date"));
        String nom = request.getParameter("nom");
        int genre = Integer.parseInt(request.getParameter("genre"));
        String voyage = request.getParameter("voyage");
        Client cl = new Client(nom,genre);
        try {
            int id = cl.insert_client(c.connectToPostgres());
            Vente v = new Vente(id,voyage,d);
            v.insert_Vente(c.connectToPostgres());
            response.sendRedirect("VenteServlet");
        } catch (Exception ex) {
            Logger.getLogger(VenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
