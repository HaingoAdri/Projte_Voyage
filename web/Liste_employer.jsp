<%--
    Document   : Liste_Activite
    Created on : 19 déc. 2023, 12:07:42
    Author     : razafinjatovo
--%>

<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="model.Vues_Employer"%>
<%@page import="model.Vues_Activite"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List<Vues_Employer> listevues = (List<Vues_Employer>) request.getAttribute("liste");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Liste activite</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron-narrow.css" rel="stylesheet">

    <script src="assets/js/ie-emulation-modes-warning.js"></script>

</head>

<body>

    <div class="container">
        <div class="header clearfix">
            <p></p>
            <p></p>
            <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="VoyageServlet">Home</a></li>
            <li role="presentation"><a href="VoyageServlet">Formulaire d'insertion Voyage</a></li>
            <li role="presentation"><a href="ListeActivite">Liste</a></li>
            <li role="presentation"><a href="Insert_Activte">Activte</a></li>
            <li role="presentation"><a href="Billets">Billet</a></li>
            <li role="presentation"><a href="Reservations">Reservation</a></li>
            <li role="presentation"><a href="Vues">Liste stock</a></li>
            <li role="presentation"><a href="Employer">Employer</a></li>
             <li role="presentation"><a href="VenteServlet">Vente</a></li>
          </ul>
        </nav>
            <h3 class="text-muted">Project Voyage</h3>
        </div>
        <hr>
        <div class="card">
            <div class="card_header">
                <h1><a href="Liste_employer"> Liste employer </a> /  Insertion d'employer :</h1>
            </div>
            <p></p>
            <div class="card_body">
                <div class="row marketing">
                    <div class="table-responsive">
                        <table class="table table-striped" id="activiteTable">
                            <thead>
                                <tr>
                                    <th>Date embauche</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Salaire Depart</th>
                                    <th>Poste</th>
                                    <th>Anciennete</th>
                                    <th>Salaire actuelle</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Vues_Employer t : listevues) {
                                    LocalDate aujourdHui = LocalDate.now();
                                    LocalDate sp = t.getEmbauche().toLocalDate();
                                    long differenceAnnees = ChronoUnit.YEARS.between(sp, aujourdHui);
                                    long differenceJour = ChronoUnit.DAYS.between(sp, aujourdHui);
                                    long differenceMois = ChronoUnit.MONTHS.between(sp, aujourdHui);
                                    double salaireactuelle = t.getSalaire();
                                    String status = "ouvrier";
                                    
                                    if(differenceAnnees >= 2){
                                        salaireactuelle = t.getSalaire() * 2;
                                        status = "senior";
                                    }
                                    

                                    if(differenceAnnees >= 5){
                                        salaireactuelle = t.getSalaire() * 3;
                                        status = "expert";
                                    }
                                  
                                %>
                                <tr>
                                    <td><%= t.getEmbauche() %></td>
                                    <td><%= t.getNom() %></td>
                                    <td><%= t.getPrenom ()%></td>
                                    <td><%= t.getSalaire() %> ar</td>
                                    <td><%= t.getPoste() %> -> <%=status%></td>
                                    <td><%= differenceAnnees %> ans, <%= differenceMois %> mois, <%= differenceJour %> jour</td>
                                    <td><%= salaireactuelle %> ar</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <footer class="footer">
            <p>&copy; 2069</p>
        </footer>
    </div> <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Récupérez les éléments d'entrée
            const destinationInput = document.querySelector('input[name="destination"]');
            const bouquetInput = document.querySelector('input[name="bouquet"]');
            const minPrixInput = document.querySelector('input[name="minPrix"]');
            const maxPrixInput = document.querySelector('input[name="maxPrix"]');
            const activiteTable = document.querySelector('#activiteTable');

            // Récupérez la liste complète des activités pour la restauration après la recherche
            const allActivities = document.querySelectorAll('#activiteTable tbody tr');

            // Ajoutez des gestionnaires d'événements pour les changements dans les entrées
            destinationInput.addEventListener('input', filterActivities);
            bouquetInput.addEventListener('input', filterActivities);
            minPrixInput.addEventListener('input', filterActivities);
            maxPrixInput.addEventListener('input', filterActivities);

            function filterActivities() {
                const enteredDestination = destinationInput.value.toLowerCase();
                const enteredBouquet = bouquetInput.value.toLowerCase();
                const enteredMinPrix = parseFloat(minPrixInput.value);
                const enteredMaxPrix = parseFloat(maxPrixInput.value);

                // Réinitialisez la liste des activités
                activiteTable.innerHTML = '<thead><tr><th>#</th><th>Dates debut</th><th>Dates fin</th><th>Destination</th><th>Bouquet</th><th>Lieu</th><th>Date activite</th><th>Lieu activite</th><th>Nom activite</th><th>Nombre de fois</th><th>Prix</th><th>Total montant activite</th></tr></thead><tbody></tbody>';

                // Parcourez toutes les activités et ajoutez celles qui correspondent aux entrées
                allActivities.forEach(function (activity) {
                    const activityDestination = activity.querySelector('td:nth-child(4)').textContent.toLowerCase();
                    const activityBouquet = activity.querySelector('td:nth-child(5)').textContent.toLowerCase();
                    const activityPrix = parseFloat(activity.querySelector('td:nth-child(11)').textContent);
                    const activityPrixM = parseFloat(activity.querySelector('td:nth-child(12)').textContent);
                    if (
                        (enteredDestination === '' || activityDestination.includes(enteredDestination)) &&
                        (enteredBouquet === '' || activityBouquet.includes(enteredBouquet)) &&
                        (isNaN(enteredMinPrix) || activityPrix >= enteredMinPrix) &&
                        (isNaN(enteredMaxPrix) || activityPrixM <= enteredMaxPrix)
                    ) {
                        activiteTable.querySelector('tbody').appendChild(activity.cloneNode(true));
                    }
                });
            }
        });
    </script>

</body>

</html>
