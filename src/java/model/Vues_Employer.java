/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ndimby Razafinjatovo
 */
public class Vues_Employer {
    
    String nom;
    String prenom;
    Date embauche;
    String poste;
    double salaire;
    int anciennete;

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

    public Date getEmbauche() {
        return embauche;
    }

    public void setEmbauche(Date embauche) {
        this.embauche = embauche;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public int getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(int anciennete) {
        this.anciennete = anciennete;
    }

    public Vues_Employer(String nom, String prenom, Date embauche, String poste, double salaire, int anciennete) {
        this.nom = nom;
        this.prenom = prenom;
        this.embauche = embauche;
        this.poste = poste;
        this.salaire = salaire;
        this.anciennete = anciennete;
    }
    
    public static  List<Vues_Employer> lietEmployer(Connection c) throws SQLException{
        List<Vues_Employer> liste = new ArrayList<>();
        String sql = "select * from view_employer";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Date embauche = rs.getDate("date_embauche");
                String poste = rs.getString("poste");
                double salaire = rs.getDouble("salaire");
                int anciennete = rs.getInt("anciennete");
                Vues_Employer employe = new Vues_Employer(nom, prenom, embauche, poste, salaire, anciennete);
                liste.add(employe);
            }
        return liste;
    }
    
}
