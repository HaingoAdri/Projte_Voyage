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
public class Transport {
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

    public Transport(String id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }

    public Transport() {
    }
    
    public Vector<Transport> listeTransport(Connection c) throws SQLException{
        Vector<Transport> allTransport = new Vector<Transport>();
        String sql ="Select * from transport";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("nomtransport");
            Transport activite = new Transport(i,d);
            allTransport.add(activite);
        }
        return allTransport;
    }
}
