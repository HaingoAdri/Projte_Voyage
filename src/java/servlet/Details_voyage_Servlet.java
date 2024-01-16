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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailVoyage;
import model.Lieu;
import model.Transport;
import model.Voyage;

/**
 *
 * @author Adrienne
 */
@WebServlet(name = "Details_voyage_Servlet", urlPatterns = {"/Details_voyage_Servlet"})
public class Details_voyage_Servlet extends HttpServlet {
    DbConnection c=new DbConnection();
    Transport t=new Transport();
    Lieu l=new Lieu();
    
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
        Vector<Transport> alltransport=t.listeTransport(c.connectToPostgres());
        request.setAttribute("alltransport",alltransport);
        
        Vector<Lieu> alllieu=l.listeLieu(c.connectToPostgres());
        request.setAttribute("alllieu",alllieu);
        
        RequestDispatcher dispact = request.getRequestDispatcher("Formulaire_Details_Voyage.jsp");
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
            Logger.getLogger(Details_voyage_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String nbrActivite = request.getParameter("nbrActivite");
        int nbrAct = Integer.parseInt(nbrActivite);

        String transport = request.getParameter("transport");
        String lieu = request.getParameter("lieu");
        String id = request.getParameter("id");
        System.out.println(id);
   
        DetailVoyage dv=new DetailVoyage(id,lieu,transport,nbrAct);
        try {
            dv.insertDetailsVoyage(c.connectToPostgres());
            response.sendRedirect("Activite_Servlet?id="+id);
        } catch (Exception ex) {
            Logger.getLogger(Details_voyage_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
