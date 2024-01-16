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
 * @author razafinjatovo
 */
public class Activite {
    String id;
    String nom;
    double prix;

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Activite(String id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }
    
    public Activite(String nom, double prix){
        this.setNom(nom);
        this.setPrix(prix);
    }

    public Activite() {
    }
    
    public Vector<Activite> listeActivite(Connection c) throws SQLException{
        Vector<Activite> allActivite = new Vector<Activite>();
        String sql ="Select * from activite";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("nomactivite");
            double prix=rs.getDouble("prix");
            Activite activite = new Activite(i,d,prix);
            allActivite.add(activite);
        }
        return allActivite;
    }
    
    public void insert_activite(Connection c) throws SQLException{
     String sql = "insert into activite (nomactivite,prix) values (?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getNom());
        st.setDouble(2, this.getPrix()); 
        st.executeUpdate();
    }
}
