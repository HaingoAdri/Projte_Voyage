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
 * @author Ndimby Razafinjatovo
 */
public class Client {
    int id;
    String nom;
    int genre;

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

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client(int id, String nom, int genre) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
    }

    public Client(String nom, int genre) {
        this.nom = nom;
        this.genre = genre;
    }

    public Client() {
    }
    
    public int insert_client(Connection c) throws SQLException{
     String sql = "insert into client (nom,genre) values (?,?) RETURNING id";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getNom());
        st.setInt(2, this.getGenre());
        System.out.println(sql);
        //st.executeUpdate();
        
        ResultSet rs = st.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt("id");
            System.out.println(id);
        }
        return id;
    }
     
      public Vector<Client> listeEmploye(Connection c,int idclient) throws SQLException{
        Vector<Client> allbillet = new Vector<Client>();
        String sql ="Select * from employe where id="+idclient;
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String nom = rs.getString("nom");
            int genre = rs.getInt("genre");
            
            Client employe = new Client(i,nom,genre);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }
      
      public void updtae_Client(Connection c,int idclient,int value) throws SQLException{
        String sql = "update client set genre= ? where id=?";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1,value);
        st.setInt(2,idclient); 
        System.out.println(sql);
        st.executeUpdate();
    }
      
      public void delete_client(Connection c,int idclient) throws SQLException{
     String sql = "delete from genre where id=";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1,idclient);
        System.out.println(sql);
        st.executeUpdate();
    }
}
