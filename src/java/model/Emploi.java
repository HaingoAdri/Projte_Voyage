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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Haingo Adrienne
 */
public class Emploi {
    int id;
    String nom;

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

    public Emploi(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Emploi(String nom) {
        this.nom = nom;
    }
    
    public List<Emploi> listeEmploi(Connection c) throws SQLException{
        List<Emploi> liste = new ArrayList<>();
        String sql = "select * from emploi";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String g = rs.getString("nom");
            Emploi p = new Emploi(i,g);
            liste.add(p);
        }
        return liste;
    }
}
