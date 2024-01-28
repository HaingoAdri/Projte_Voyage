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
import java.util.Vector;

/**
 *
 * @author razafinjatovo
 */
public class Voyage {
    String id;
    String destination;
    String age;
    String bouquet;
    int groupe;
    int persgroupe;
    Date dateDebut;
    Date datefin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBouquet() {
        return bouquet;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }

    public int getGroupe() {
        return groupe;
    }

    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }

    public int getPersgroupe() {
        return persgroupe;
    }

    public void setPersgroupe(int persgroupe) {
        this.persgroupe = persgroupe;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Voyage(String id, String destination, String age, String bouquet, int groupe, int persgroupe, Date dateDebut, Date datefin) {
        this.setId(id);
        this.setDestination(destination);
        this.setAge(age);
        this.setBouquet(bouquet);
        this.setGroupe(groupe);
        this.setPersgroupe(persgroupe);
        this.setDateDebut(dateDebut);
        this.setDatefin(datefin);
    }

    public Voyage(String destination, String age, String bouquet, int groupe, int persgroupe, Date dateDebut, Date datefin) {
        this.setDestination(destination);
        this.setAge(age);
        this.setBouquet(bouquet);
        this.setGroupe(groupe);
        this.setPersgroupe(persgroupe);
        this.setDateDebut(dateDebut);
        this.setDatefin(datefin);
    }
    

    public Voyage(){
    }
    
    public  Vector<Voyage> listeVoyage(Connection c) throws SQLException{
        Vector<Voyage> allVoyage = new Vector<Voyage>();
        String sql ="Select * from voyage";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("iddestination");
            String b = rs.getString("idtrancheage");
            String g = rs.getString("idbouquet");
            int h = rs.getInt("groupe");
            int nbr = rs.getInt("persgroupe");
            Date m = rs.getDate("datedebut");
            Date j = rs.getDate("datefin");
            Voyage voyage = new Voyage(i,d,b,g,h,nbr,m,j);
            allVoyage.add(voyage);
        }
        return allVoyage;
    }
    
    public Vector<Voyage> listeVoyageWhere(Connection c,String id) throws SQLException{
        Vector<Voyage> allVoyage = new Vector<Voyage>();
        String sql ="Select * from voyage where id='"+id+"'";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("iddestination");
            String b = rs.getString("idtrancheage");
            String g = rs.getString("idbouquet");
            int h = rs.getInt("groupe");
            int nbr = rs.getInt("persgroupe");
            Date m = rs.getDate("datedebut");
            Date j = rs.getDate("datefin");
            Voyage voyage = new Voyage(i,d,b,g,h,nbr,m,j);
            allVoyage.add(voyage);
        }
        return allVoyage;
    }
    
    public String insertVoyage(Connection c) throws SQLException{
        String sql = "insert into voyage (iddestination, idtrancheage, idbouquet, groupe, persgroupe, datedebut, datefin) values (?,?,?,?,?,?,?) RETURNING id";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.destination);
        st.setString(2, this.age);
        st.setString(3, this.bouquet);
        st.setInt(4,this.groupe);
        st.setInt(5, this.persgroupe);
        st.setDate(6,this.dateDebut);
        st.setDate(7,this.datefin);
        //st.executeUpdate();
        ResultSet resultSet = st.executeQuery();
        String id = "";
            // Récupérer l'ID généré
            if (resultSet.next()) {
                id = resultSet.getString("id");
                System.out.println("ID généré : " + id);
            }
       return id;
    }
}
