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
public class DetailVoyage {
    String id;
    String voyage;
    String lieu;
    String transport;
    int nbractivite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public int getNbractivite() {
        return nbractivite;
    }

    public void setNbractivite(int nbractivite) {
        this.nbractivite = nbractivite;
    }

    public DetailVoyage(String id, String voyage, String lieu, String transport, int nbractivite) {
        this.setId(id);
        this.setVoyage(voyage);
        this.setLieu(lieu);
        this.setTransport(transport);
        this.setNbractivite(nbractivite);
    }

    public DetailVoyage(String voyage, String lieu, String transport, int nbractivite) {
        this.setVoyage(voyage);
        this.setLieu(lieu);
        this.setTransport(transport);
        this.setNbractivite(nbractivite);
    }
    
    
   
    public DetailVoyage() {
    }
    
    public Vector<DetailVoyage> listeDetailVoyage(Connection c) throws SQLException{
        Vector<DetailVoyage> allDetailVoyage = new Vector<DetailVoyage>();
        String sql ="Select * from detailvoyage";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("idvoyage");
            String g = rs.getString("idlieu");
            String h = rs.getString("idtransport");
            int nbr = rs.getInt("nbractivite");
            DetailVoyage detailvoyage = new DetailVoyage(i,d,g,h,nbr);
            allDetailVoyage.add(detailvoyage);
        }
        return allDetailVoyage;
    }
    
    public void insertDetailsVoyage(Connection c) throws SQLException{
        String sql  = "insert into detailvoyage (idvoyage, idlieu, idtransport, nbractivite) values (?,?,?,?)";
        PreparedStatement st = c.prepareStatement(sql); 
        st.setString(1, this.voyage);
        st.setString(2, this.lieu);
        st.setString(3, this.transport);
        st.setInt(4, this.nbractivite);
        st.executeUpdate();
    }
}
