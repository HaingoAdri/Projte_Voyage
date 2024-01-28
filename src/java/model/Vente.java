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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Ndimby Razafinjatovo
 */
public class Vente {
    int id;
    int idclient;
    String idvoyage;
    Date datevente;
    String nom;
    double pourcentage;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    

    public Vente(int id, int idclient, String idvoyage, Date datevente) {
        this.id = id;
        this.idclient = idclient;
        this.idvoyage = idvoyage;
        this.datevente = datevente;
    }
    
    public Vente(int idclient, String idvoyage, Date datevente) {
        this.idclient = idclient;
        this.idvoyage = idvoyage;
        this.datevente = datevente;
    }

    public Vente(int idclient, String idvoyage) {
        this.idclient = idclient;
        this.idvoyage = idvoyage;
    }

    public Vente(int idclient, String idvoyage, String nom, double p) {
        this.idclient = idclient;
        this.idvoyage = idvoyage;
        this.nom = nom;
        this.pourcentage = p;
    }
    
    

    public Vente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getIdvoyage() {
        return idvoyage;
    }

    public void setIdvoyage(String idvoyage) {
        this.idvoyage = idvoyage;
    }

    public Date getDatevente() {
        return datevente;
    }

    public void setDatevente(Date datevente) {
        this.datevente = datevente;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    
    
    
    
     public int insert_Vente(Connection c) throws SQLException{
     String sql = "insert into vente (idclient,idvoyage,datevente) values (?,?,?) RETURNING id";
        PreparedStatement st = c.prepareStatement(sql);
        st.setInt(1, this.getIdclient());
        st.setString(2, this.getIdvoyage());
        st.setDate(3,this.getDatevente());
        
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
     
      public Vector<Vente> liste_vente_genre(Connection c) throws SQLException{
        Vector<Vente> allbillet = new Vector<Vente>();
        String sql ="Select * from vente";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            int i = rs.getInt("id");
            int nom = rs.getInt("idclient");
            String idvoyage=rs.getString("idvoyage");
            Date daty=rs.getDate("datevente");
            
            Vente employe = new Vente(i,nom,idvoyage,daty);
            allbillet.add(employe);
        }
        System.out.println(sql);
        return allbillet;
    }
      
    public static double total_vente_par_voyage(Connection c, String voyage) throws SQLException{

         String sql ="Select sum(nombre) from statistique where idvoyage='"+voyage+"'";
         Statement st  = c.createStatement();
         ResultSet rs  = st.executeQuery(sql);
         double somme=0;

         while(rs.next()){
             double i = rs.getDouble("sum");
             somme=i;
         }
         System.out.println(sql);
         return somme;
    }
    public static double somme_par_genre(Connection c , int idGenre) throws SQLException{
        String sql ="select genre, idvoyage, sum(nombre)\n" +
             "from statistique where genre="+idGenre;
         Statement st  = c.createStatement();
         ResultSet rs  = st.executeQuery(sql);
         double somme=0;

         while(rs.next()){
             double i = rs.getDouble("sum");
             somme=i;
         }
         System.out.println(sql);
         return somme;
    }

    public double get_pourcentage(Connection c, String v, int idGenre) throws SQLException{
        double total=total_vente_par_voyage(c,v);
        double parGenre=somme_par_genre(c,idGenre);
        double pourcentage=(100*parGenre)/total;
        return pourcentage;
    }
    
    public static List<Vente> getStatistique(Connection c) throws SQLException{
        String sql = "SELECT\n" +
"    nom,\n" +
"    idvoyage,\n" +
"    SUM(nombre) AS nombre,\n" +
"    ROUND((SUM(nombre)::numeric / total::numeric) * 100, 2) AS pourcentage\n" +
"FROM\n" +
"    (\n" +
"        SELECT\n" +
"            g.nom,\n" +
"            idvoyage,\n" +
"            nombre,\n" +
"            SUM(nombre) OVER (PARTITION BY idvoyage) AS total\n" +
"        FROM\n" +
"            statistique\n" +
"            JOIN genre AS g ON statistique.genre = g.id\n" +
"    ) AS sous_requete\n" +
"GROUP BY\n" +
"    idvoyage,\n" +
"    nom,\n" +
"    total";
        List<Vente> liste = new ArrayList<>();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int nbr = rs.getInt("nombre");
            String voyage = rs.getString("idvoyage");
            String genre = rs.getString("nom");
            double por = rs.getDouble("pourcentage");
            Vente v = new Vente(nbr,voyage,genre,por);
            liste.add(v);
        }
        return liste;
    }
}
