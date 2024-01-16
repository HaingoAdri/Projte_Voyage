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
import java.sql.SQLException;
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
import model.Bouquet;
import model.Destination;
import model.TrancheAge;
import model.Voyage;

/**
 *
 * @author razafinjatovo
 */
@WebServlet(name = "VoyageServlet", urlPatterns = {"/VoyageServlet"})
public class VoyageServlet extends HttpServlet {
    DbConnection c = new DbConnection();
    Bouquet b = new Bouquet();
    Destination d= new Destination();
    TrancheAge a=new TrancheAge();
    Voyage f = new Voyage();
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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        Vector<Bouquet> allBouquet = b.listeBouquet(c.connectToPostgres());
        request.setAttribute("allBouquet",allBouquet);
    
        Vector<Destination> alldestination= d.listeDestination(c.connectToPostgres());
        request.setAttribute("alldestination",alldestination);
        
        Vector<TrancheAge> allage=a.listeTrancheAge(c.connectToPostgres());
        request.setAttribute("allage",allage);
        
        RequestDispatcher dispact = request.getRequestDispatcher("Forulaire_Voyage.jsp");
        dispact.forward(request, response);
        
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
        } catch (SQLException ex) {
            Logger.getLogger(VoyageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VoyageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        String debut = request.getParameter("dateDebut");
        String fin = request.getParameter("datefin");
        String destination = request.getParameter("destination");
        String age = request.getParameter("age");
        String bouquet = request.getParameter("bouquet");
        String grou = request.getParameter("nbr");
        String pers = request.getParameter("nbrPers");
        Date d1 = Date.valueOf(debut);
        Date d2 = Date.valueOf(fin);
        int groupe = Integer.parseInt(grou);
        int personne = Integer.parseInt(pers);
        Voyage v = new Voyage(destination,age,bouquet,groupe,personne,d1,d2);
        try {
            String id=v.insertVoyage(c.connectToPostgres());
            response.sendRedirect("Details_voyage_Servlet?id="+id);
        } catch (Exception ex) {
            Logger.getLogger(VoyageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
