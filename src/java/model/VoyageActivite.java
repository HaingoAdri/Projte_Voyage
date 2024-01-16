/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

/**
 *
 * @author razafinjatovo
 */
public class VoyageActivite {
    String id;
    String voyage;
    String activte;
    Date date;
    Time timedebut;
    Time timefin;
    String lieu;
    int nombre;

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

    public String getActivte() {
        return activte;
    }

    public void setActivte(String activte) {
        this.activte = activte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimedebut() {
        return timedebut;
    }

    public void setTimedebut(Time timedebut) {
        this.timedebut = timedebut;
    }

    public Time getTimefin() {
        return timefin;
    }

    public void setTimefin(Time timefin) {
        this.timefin = timefin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    

    public VoyageActivite(String id, String voyage, String activte, Date date, Time timedebut, Time timefin, String lieu) {
        this.setId(id);
        this.setVoyage(voyage);
        this.setActivte(activte);
        this.setDate(date);
        this.setTimedebut(timedebut);
        this.setTimefin(timefin);
        this.setLieu(lieu);
    }
    
    public VoyageActivite(String id, String voyage, String activte, Date date, Time timedebut, Time timefin, String lieu, int nbr) {
        this.setId(id);
        this.setVoyage(voyage);
        this.setActivte(activte);
        this.setDate(date);
        this.setTimedebut(timedebut);
        this.setTimefin(timefin);
        this.setLieu(lieu);
        this.setNombre(nbr);
    }

    public VoyageActivite(String voyage, String activte, Date date, Time timedebut, Time timefin, String lieu, int nombre) {
        this.setVoyage(voyage);
        this.setActivte(activte);
        this.setDate(date);
        this.setTimedebut(timedebut);
        this.setTimefin(timefin);
        this.setLieu(lieu);
        this.setNombre(nombre);
    }
    

    public VoyageActivite() {
    }
    
     public Vector<VoyageActivite> listeVoyageActivite(Connection c) throws SQLException{
        Vector<VoyageActivite> allVoyageActivite = new Vector<VoyageActivite>();
        String sql ="Select * from voyageactivite";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("idvoyage");
            String b = rs.getString("idactivite");
            String g = rs.getString("lieu");
            Time h = rs.getTime("heuredebut");
            Time nbr = rs.getTime("heurefin");
            Date m = rs.getDate("daty");
            VoyageActivite voyageactivite = new VoyageActivite(i,d,b,m,h,nbr,g);
            allVoyageActivite.add(voyageactivite);
        }
        return allVoyageActivite;
    }
     
      public Vector<VoyageActivite> listeVoyageActiviteById(Connection c, String voyage) throws SQLException{
        Vector<VoyageActivite> allVoyageActivite = new Vector<VoyageActivite>();
        String sql ="Select * from voyageactivite where idvoyage = '"+voyage+"'";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("idvoyage");
            String b = rs.getString("idactivite");
            String g = rs.getString("lieu");
            Time h = rs.getTime("heuredebut");
            Time nbr = rs.getTime("heurefin");
            Date m = rs.getDate("daty");
            int nbrs = rs.getInt("nombre");
            VoyageActivite voyageactivite = new VoyageActivite(i,d,b,m,h,nbr,g,nbrs);
            allVoyageActivite.add(voyageactivite);
        }
        System.out.println(sql);
        return allVoyageActivite;
    }
     
    public void insertVoyageActivite(Connection c) throws SQLException{
        String sql = "insert into voyageactivite(idvoyage, idactivite, daty, heuredebut, heurefin, lieu,nombre) values (?,?,?,?,?,?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.voyage);
        st.setString(2, this.activte);
        st.setDate(3,this.date);
        st.setTime(4, this.timedebut);
        st.setTime(5, this.timefin);
        st.setString(6, this.lieu);
        st.setInt(7, this.nombre);
        st.executeUpdate();
    }
}
