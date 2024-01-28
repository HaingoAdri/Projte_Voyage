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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employe;
import model.Poste;
import model.Salaire;

/**
 *
 * @author Ndimby Razafinjatovo
 */
@WebServlet(name = "Employer", urlPatterns = {"/Employer"})
public class Employer extends HttpServlet {
    DbConnection c = new DbConnection();
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
        List<Poste> poste = Poste.listeEmploye(c.connectToPostgres());
        request.setAttribute("poste", poste);
        RequestDispatcher dispact = request.getRequestDispatcher("Insert_Employer.jsp");
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
            Logger.getLogger(Employer.class.getName()).log(Level.SEVERE, null, ex);
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
        String date = request.getParameter("date");
        Date embauche = Date.valueOf(date);
        String nom = request.getParameter("nom");
        String prenom= request.getParameter("prenom");
        int poste = Integer.parseInt(request.getParameter("poste"));
        double salaire= Double.parseDouble(request.getParameter("salaire"));
        
        Employe e=new Employe(nom,prenom,embauche,poste);
        
        try {
            int id = e.insert_employe(c.connectToPostgres());
            Salaire s = new Salaire(salaire, id);
            s.insert_salaire(c.connectToPostgres());
            response.sendRedirect("Employer");
        } catch (Exception ex) {
            Logger.getLogger(Employer.class.getName()).log(Level.SEVERE, null, ex);
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
