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
public class Historique {
    int id;
    String idactivite;
    int quantite;
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdactivite() {
        return idactivite;
    }

    public void setIdactivite(String idactivite) {
        this.idactivite = idactivite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Historique(String idactivite, int quantite) {
        this.idactivite = idactivite;
        this.quantite = quantite;
    }

    public Historique(int id, String idactivite, int quantite) {
        this.id = id;
        this.idactivite = idactivite;
        this.quantite = quantite;
    }

    public Historique() {
    }
    
    public Vector<Historique> listeHistorique(Connection c) throws SQLException{
        Vector<Historique> allbillet = new Vector<Historique>();
        String sql ="Select * from historiquebillet";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String d = rs.getString("idactivite");
            int prix=rs.getInt("quantite");
             Historique billet = new Historique(i,d,prix);
            allbillet.add(billet);
        }
        System.out.println(sql);
        return allbillet;
    }
    
    public void insert_Historique(Connection c) throws SQLException{
     String sql = "insert into historiquebillet (idactivite,quantite) values (?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getIdactivite());
        st.setInt(2, this.getQuantite()); 
        System.out.println(sql);
        st.executeUpdate();
    }
}
