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
import java.sql.Time;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Activite;
import model.VoyageActivite;

/**
 *
 * @author Adrienne
 */
@WebServlet(name = "Activite_Servlet", urlPatterns = {"/Activite_Servlet"})
public class Activite_Servlet extends HttpServlet {
    
    DbConnection c=new DbConnection();
    Activite a=new Activite();

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
        response.setContentType("text/html;charset=UTF-8");
        Vector<Activite> allactivity= a.listeActivite(c.connectToPostgres());
        
        request.setAttribute("allactivity",allactivity);
        
        RequestDispatcher dispact = request.getRequestDispatcher("Formulaire_Details_Activite.jsp");
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
        } catch (Exception ex) {
            Logger.getLogger(Activite_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
        String id = request.getParameter("id");
    
        String dateActivite = request.getParameter("dateActivite");
        String heureDebut = request.getParameter("heuredebut");
        heureDebut += ":00";
        String heureFin = request.getParameter("heurefin");
        heureFin += ":00";
        String activite = request.getParameter("activite");
        String nbr=request.getParameter("nbrfois");
        String lieu = request.getParameter("lieuactivite");
        Date debut=Date.valueOf(dateActivite);
        System.out.println(heureDebut);
        Time hDebut = Time.valueOf(heureDebut);
        Time hFin = Time.valueOf(heureFin);
        int nombre = Integer.parseInt(nbr);
        VoyageActivite va = new VoyageActivite(id,activite,debut, hDebut, hFin,lieu,nombre);
        try {
            va.insertVoyageActivite(c.connectToPostgres());
            
        } catch (Exception ex) {
            Logger.getLogger(Activite_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("VoyageServlet");
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
