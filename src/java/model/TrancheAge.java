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
public class TrancheAge {
    String id;
    String nom;
    int min;
    int max;

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

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public TrancheAge(String id, String nom, int min, int max) {
        this.setId(id);
        this.setNom(nom);
        this.setMin(min);
        this.setMax(max);
    }

    public TrancheAge() {
    }
    
    public Vector<TrancheAge> listeTrancheAge(Connection c) throws SQLException{
        Vector<TrancheAge> allTrancheAge = new Vector<TrancheAge>();
        String sql ="Select * from trancheage";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("nomage");
            int min = rs.getInt("min");
            int max = rs.getInt("max");
            TrancheAge age = new TrancheAge(i,d,min,max);
            allTrancheAge.add(age);
        }
        return allTrancheAge;
    }
}
