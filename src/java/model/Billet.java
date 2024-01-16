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
public class Billet {
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

    public Billet(String idactivite, int quantite) {
        this.idactivite = idactivite;
        this.quantite = quantite;
    }

    public Billet(int id, String idactivite, int quantite) {
        this.id = id;
        this.idactivite = idactivite;
        this.quantite = quantite;
    }

    public Billet() {
    }
    
    public Vector<Billet> listeBillet(Connection c) throws SQLException{
        Vector<Billet> allbillet = new Vector<Billet>();
        String sql ="Select * from billet";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String d = rs.getString("idactivite");
            int prix=rs.getInt("quantite");
             Billet billet = new Billet(i,d,prix);
            allbillet.add(billet);
        }
        System.out.println(sql);
        return allbillet;
    }
    
    public Billet totalbillet(Connection c,String id) throws SQLException{
        
        String sql ="Select idactivite, sum(quantite) as quantite from billet where idactivite='"+id+"' group by idactivite";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        Billet jj = null;
        int count=0;
        while(rs.next()){
            String act = rs.getString("idactivite");
            int i = rs.getInt("quantite");
            jj = new Billet(act,i);
        }
        System.out.println(sql);
        return jj;
    }
    
    public void insert_billet(Connection c) throws SQLException{
     String sql = "insert into billet (idactivite,quantite) values (?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getIdactivite());
        st.setInt(2, this.getQuantite());
        System.out.println(sql);
        st.executeUpdate();
    }
    
    public void updateWhere(Connection c , String voyage, int quantite) throws SQLException{
        String sql = "update billet set quantite="+quantite+" where idactivite = '"+voyage+"'";
        PreparedStatement st = c.prepareStatement(sql);
        System.out.println(sql);
        st.executeUpdate();
    }
}
