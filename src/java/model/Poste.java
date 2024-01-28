/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Ndimby Razafinjatovo
 */
public class Poste {
    int id;
    String nom;

    public Poste(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Poste(String nom) {
        this.nom = nom;
    }

    public Poste() {
    }

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
    
    public static Vector<Poste> listeEmploye(Connection c) throws SQLException{
        Vector<Poste> allbillet = new Vector<Poste>();
        String sql ="Select * from poste";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String nom = rs.getString("nom");
            Poste employe = new Poste(i,nom);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }
}
