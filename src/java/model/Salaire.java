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
public class Salaire {
    int id;
    double salaire;
    int employe;

    public Salaire(int id, double salaire, int employe) {
        this.id = id;
        this.salaire = salaire;
        this.employe = employe;
    }

    public Salaire() {
    }

    public Salaire(double salaire, int employe) {
        this.salaire = salaire;
        this.employe = employe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }
    
    
     public void insert_salaire(Connection c) throws SQLException{
     String sql = "insert into salaire (salaire,employe) values (?,?)";
        PreparedStatement st = c.prepareStatement(sql);
        st.setDouble(1, this.getSalaire());
        st.setInt(2, this.getEmploye());
        System.out.println(sql);
        st.executeUpdate();
    }
     
      public Vector<Salaire> listesalaire(Connection c,int idemploye) throws SQLException{
        Vector<Salaire> allbillet = new Vector<Salaire>();
        String sql ="Select * from salaire where id="+idemploye;
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            double salaire = rs.getDouble("salaire");
            int idemp=rs.getInt("employe");
           Salaire employe = new Salaire(i,salaire,idemp);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }
        
}
