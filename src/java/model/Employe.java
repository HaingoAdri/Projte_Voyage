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
public class Employe {
    int id;
    String nom;
    String prenom;
    Date date_embauche; 
    int poste;

    public Employe(int id, String nom, String prenom, Date date_embauche, int poste) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_embauche = date_embauche;
        this.poste = poste;
    }

    public Employe(String nom, String prenom, Date date_embauche, int poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_embauche = date_embauche;
        this.poste = poste;
    }

    public Employe() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public int getPoste() {
        return poste;
    }

    public void setPoste(int poste) {
        this.poste = poste;
    }
    
    public int insert_employe(Connection c) throws SQLException{
     String sql = "insert into employe (nom,prenom,date_embauche,poste) values (?,?,?,?) RETURNING id";
        PreparedStatement st = c.prepareStatement(sql);
        st.setString(1, this.getNom());
        st.setString(2, this.getPrenom());
        st.setDate(3, this.getDate_embauche());
        st.setInt(4, this.getPoste());
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
     
      public Vector<Employe> listeEmploye(Connection c,int idemploye) throws SQLException{
        Vector<Employe> allbillet = new Vector<Employe>();
        String sql ="Select * from employe where id="+idemploye;
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Date date=rs.getDate("date_embauche");
            int poste=rs.getInt("poste");
             Employe employe = new Employe(i,nom,prenom,date,poste);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }

    
    
}
