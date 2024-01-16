/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author razafinjatovo
 */
public class BouquetActivite {
    String id;
    String activite;
    String transport;
    String bouquet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getBouquet() {
        return bouquet;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }
    

    public BouquetActivite(String id, String activite, String transport, String bouquet) {
        this.setId(id);
        this.setActivite(activite);
        this.setTransport(transport);
        this.setBouquet(bouquet);
    }

    public BouquetActivite() {
    }
    
    public Vector<BouquetActivite> listeBouquetActivite(Connection c) throws SQLException{
        Vector<BouquetActivite> allBouquetActivite = new Vector<BouquetActivite>();
        String sql ="Select * from bouquetactivite";
        Statement st  = c.createStatement();
        ResultSet rs  = st.executeQuery(sql);
        while(rs.next()){
            String i = rs.getString("id");
            String d = rs.getString("idactivite");
            String g = rs.getString("idtransport");
            String v = rs.getString("idbouquet");
            BouquetActivite bouquetactivite = new BouquetActivite(i,d,g,v);
            allBouquetActivite.add(bouquetactivite);
        }
        return allBouquetActivite;
    }
    
    
}
