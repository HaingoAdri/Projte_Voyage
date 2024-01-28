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
 * @author Ndimby Razafinjatovo
 */
public class Genre {
    int id;
    String nom;

    public Genre(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Genre(String nom) {
        this.nom = nom;
    }

    public Genre() {
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
    
    public int insert_Genre(Connection c) throws SQLException{
     String sql = "insert into genre (nom) values (?) RETURNING id";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getNom());
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
     
      public static Vector<Genre> liste_Genre(Connection c) throws SQLException{
        Vector<Genre> allbillet = new Vector<Genre>();
        String sql ="Select * from genre";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String nom = rs.getString("nom");
            
            Genre employe = new Genre(i,nom);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }
      
      public void updtae_Genre(Connection c,int idgenre,String value) throws SQLException{
        String sql = "update genre set nom= ? where id=?";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1,value);
        st.setInt(2,idgenre); 
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
