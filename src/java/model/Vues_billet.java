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
import java.sql.Time;
import java.util.Vector;

/**
 *
 * @author Adrienne
 */
public class Vues_billet {
    String id;
    private String nomBouquet;
    private int nombre;
    private int no;
    private int nomm;
    private String client;
    // Constructeur

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomBouquet() {
        return nomBouquet;
    }

    public void setNomBouquet(String nomBouquet) {
        this.nomBouquet = nomBouquet;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNomm() {
        return nomm;
    }

    public void setNomm(int nomm) {
        this.nomm = nomm;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    

    public Vues_billet(String id, String nomBouquet, int nombre, int no, int nomm, String cl) {
        this.id = id;
        this.nomBouquet = nomBouquet;
        this.nombre = nombre;
        this.no = no;
        this.nomm = nomm;
        this.client = cl;
    }
    
    public Vues_billet() {
    }
    
    public Vector<Vues_billet> getAllActivites(Connection c) throws SQLException {
        Vector<Vues_billet> activites = new Vector<Vues_billet>();

            String sql = "SELECT * FROM vues";
            PreparedStatement preparedStatement = c.prepareStatement(sql);

            // Exécuter la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            // Traiter les résultats
            while (resultSet.next()) {
                String id = resultSet.getString("idvoyage");
                String lieu = resultSet.getString("nomactivite");
                int nombre = resultSet.getInt("nombre");
                int h = resultSet.getInt("quantite");
                int reste = resultSet.getInt("reste");
                String cl = resultSet.getString("nomclient");
                // Créer un objet Activite et l'ajouter à la liste
                Vues_billet b = new Vues_billet(id,lieu,nombre,h,reste,cl);
                activites.add(b);
            }

            // Fermer les ressources
        return activites;
    }
    
        
}
