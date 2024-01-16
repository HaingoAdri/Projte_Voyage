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
public class Lieu {
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

    public Lieu() {
    }

    public Lieu(String id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }
    
    public Vector<Lieu> listeLieu(Connection c) throws SQLException{
        Vector<Lieu> allLieu = new Vector<Lieu>();
        String sql ="Select * from lieu";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("nomlieu");
            Lieu lieu = new Lieu(i,d);
            allLieu.add(lieu);
        }
        return allLieu;
    }
}
