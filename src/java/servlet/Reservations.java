/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connexion.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
import model.Activite;
import model.Billet;
import model.Historique;
import model.Reservation;
import model.Voyage;
import model.VoyageActivite;

/**
 *
 * @author Adrienne
 */
@WebServlet(name = "Reservations", urlPatterns = {"/Reservations"})
public class Reservations extends HttpServlet {
    Activite a=new Activite();
    DbConnection c=new DbConnection();
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
        Vector<Voyage> listeVoyage = v.listeVoyage(c.connectToPostgres());
        request.setAttribute("voyage", listeVoyage);
        RequestDispatcher dispacth = request.getRequestDispatcher("Reservation.jsp");
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
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
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
        String nom = request.getParameter("nom");
        String voyage = request.getParameter("voyage");
        VoyageActivite h = new VoyageActivite();
        Reservation r = new Reservation(nom,voyage);
        out.println(nom);
        try {
            List<VoyageActivite> liste = h.listeVoyageActiviteById(c.connectToPostgres(), voyage);
            Billet billet = new Billet();
            
            for(VoyageActivite l : liste){
                out.println(l.getNombre());
                Billet somme = billet.totalbillet(c.connectToPostgres(), l.getActivte());
                if(somme.getQuantite()<l.getNombre()){
                    IllegalArgumentException e = new IllegalArgumentException("Quantite insuffusante pour l'activite "+l.getActivte());
                    response.sendRedirect("Reservations?Error="+e.getMessage());
                }else{
                    r.insert_reservation(c.connectToPostgres());
                    int nouveau = somme.getQuantite() - l.getNombre();
                    Historique hist = new Historique(l.getActivte(),somme.getQuantite());
                    hist.insert_Historique(c.connectToPostgres());
                    billet.updateWhere(c.connectToPostgres(), l.getActivte(), nouveau);
                    response.sendRedirect("Reservations?success=true");
                }
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
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
