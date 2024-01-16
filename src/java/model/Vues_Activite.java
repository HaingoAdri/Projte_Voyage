package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

public class Vues_Activite {
    String id;
    private Date dateDebut;
    private Date dateFin;
    private String nomDestination;
    private String nomBouquet;
    private String nomLieu;
    private Date dateActivite;
    private Time heureDebut;
    private Time heureFin;
    private String lieu;
    private int nombre;
    private String nomActivite;
    private double total;
    private double prix;
    // Constructeur
    public Vues_Activite(String id,Date dateDebut, Date dateFin, String nomDestination, String nomBouquet,
                    String nomLieu, Date dateActivite, Time heureDebut, Time heureFin,
                    String lieu, int nombre, String nomActivite,double total, double prix) {
        this.setId(id);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomDestination = nomDestination;
        this.nomBouquet = nomBouquet;
        this.nomLieu = nomLieu;
        this.dateActivite = dateActivite;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.lieu = lieu;
        this.nombre = nombre;
        this.nomActivite = nomActivite;
        this.setTotal(total);
        this.setPrix(prix);
    }

    public Vues_Activite() {
        
    }

    private Vues_Activite(String id, double prix) {
        this.setId(id);
        this.setPrix(prix);
    }
    

    // Getters
    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getNomDestination() {
        return nomDestination;
    }

    public String getNomBouquet() {
        return nomBouquet;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public Date getDateActivite() {
        return dateActivite;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public String getLieu() {
        return lieu;
    }

    public int getNombre() {
        return nombre;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public Vector<Vues_Activite> getAllActivites(Connection c) throws SQLException {
        Vector<Vues_Activite> activites = new Vector<Vues_Activite>();

            String sql = "SELECT * FROM vues_activite";
            PreparedStatement preparedStatement = c.prepareStatement(sql);

            // Exécuter la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            // Traiter les résultats
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                Date dateDebut = resultSet.getDate("datedebut");
                Date dateFin = resultSet.getDate("datefin");
                String nomDestination = resultSet.getString("nomdestination");
                String nomBouquet = resultSet.getString("nombouquet");
                String nomLieu = resultSet.getString("nomlieu");
                Date dateActivite = resultSet.getDate("daty");
                Time heureDebut = resultSet.getTime("heuredebut");
                Time heureFin = resultSet.getTime("heurefin");
                String lieu = resultSet.getString("lieu");
                int nombre = resultSet.getInt("nombre");
                String nomActivite = resultSet.getString("nomactivite");
                double tota = resultSet.getDouble("totalactivite");
                double prix = resultSet.getDouble("prix");

                // Créer un objet Activite et l'ajouter à la liste
                Vues_Activite activite = new Vues_Activite(id,dateDebut, dateFin, nomDestination, nomBouquet,
                        nomLieu, dateActivite, heureDebut, heureFin,
                        lieu, nombre, nomActivite,tota,prix);
                activites.add(activite);
            }

            // Fermer les ressources
        return activites;
    }
    
    public Vector<Vues_Activite> getAllActivitesSum(Connection c) throws SQLException {
        Vector<Vues_Activite> activites = new Vector<Vues_Activite>();

            String sql = "select id,sum(totalactivite) from vues_activite group by id";
            PreparedStatement preparedStatement = c.prepareStatement(sql);

            // Exécuter la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            // Traiter les résultats
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                double prix = resultSet.getDouble("sum");

                // Créer un objet Activite et l'ajouter à la liste
                Vues_Activite activite = new Vues_Activite(id,prix);
                activites.add(activite);
            }

            // Fermer les ressources
        return activites;
    }
    
    
}
