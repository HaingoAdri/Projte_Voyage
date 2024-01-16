/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author razafinjatovo
 */
public class Bouquet {
    String id;
    String nom;

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

    public Bouquet(String id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }

    public Bouquet() {
    }
    
    public Vector<Bouquet> listeBouquet(Connection c) throws SQLException{
        Vector<Bouquet> allBouquet = new Vector<Bouquet>();
        String sql ="Select * from bouquet";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("nombouquet");
            Bouquet bouquet = new Bouquet(i,d);
            allBouquet.add(bouquet);
        }
        return allBouquet;
    }
}
