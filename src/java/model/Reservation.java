/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Adrienne
 */
public class Reservation {
    int id;
    String nom;
    String idvoyage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }

    public Reservation(int id, String nom, String idvoyage) {
        this.id = id;
        this.nom = nom;
        this.idvoyage = idvoyage;
    }

    public Reservation(String nom, String idvoyage) {
        this.nom = nom;
        this.idvoyage = idvoyage;
    }

    public Reservation() {
    }
    
    public Vector<Reservation> listeReservation(Connection c) throws SQLException{
        Vector<Reservation> allreservation = new Vector<Reservation>();
        String sql ="Select * from reservation";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String d = rs.getString("nomclient");
            String prix=rs.getString("idvoyage");
             Reservation reservation = new Reservation(i,d,prix);
            allreservation.add(reservation);
        }
        System.out.println(sql);
        return allreservation;
    }
    
    public void insert_reservation(Connection c) throws SQLException{
     String sql = "insert into reservation (nomclient,idvoyage) values (?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getNom());
        st.setString(2, this.getIdvoyage()); 
        System.out.println(sql);
        st.executeUpdate();
    }
}
