<%--
    Document   : Liste_Activite
    Created on : 19 déc. 2023, 12:07:42
    Author     : razafinjatovo
--%>

<%@page import="model.Vues_Activite"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Vector<Vues_Activite> listevues = (Vector<Vues_Activite>) request.getAttribute("liste"); %>
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
          </ul>

            </nav>
            <h3 class="text-muted">Project Voyage</h3>
        </div>
        <hr>
        <div class="card">
            <div class="card_header">
                <h1>Liste activite du voyage (lieu):</h1>
                <div class="row marketing">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Destination</th>
                                    <th>Bouquet</th>
                                    <th>Min prix</th>
                                    <th>Max prix</th>
                                </tr>
                            </thead>
                            <tbody>
                                <form action="" method="POST">
                                    <tr>
                                        <td>
                                            <input type="text" name="destination" class="form-control"
                                                placeholder="Destination">
                                        </td>
                                        <td>
                                            <input type="text" name="bouquet" class="form-control"
                                                placeholder="Bouquet">
                                        </td>
                                        <td>
                                            <input type="number" name="minPrix" class="form-control"
                                                placeholder="Min Prix">
                                        </td>
                                        <td>
                                            <input type="number" name="maxPrix" class="form-control"
                                                placeholder="Max Prix">
                                        </td>
                                    </tr>
                                    
                                </form>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <p></p>
            <div class="card_body">
                <div class="row marketing">
                    <div class="table-responsive">
                        <table class="table table-striped" id="activiteTable">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Dates debut</th>
                                    <th>Dates fin</th>
                                    <th>Destination</th>
                                    <th>Bouquet</th>
                                    <th>Lieu</th>
                                    <th>Date activite</th>
                                    <th>Lieu activite</th>
                                    <th>Nom activite</th>
                                    <th>Nombre de fois</th>
                                    <th>Prix unitaire</th>
                                    <th>Total montant activite</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Vues_Activite t : listevues) { %>
                                <tr>
                                    <td><%= t.getId() %></td>
                                    <td><%= t.getDateDebut() %></td>
                                    <td><%= t.getDateFin() %></td>
                                    <td><%= t.getNomDestination() %></td>
                                    <td><%= t.getNomBouquet() %></td>
                                    <td><%= t.getNomLieu() %>.</td>
                                    <td><%= t.getDateActivite() %></td>
                                    <td><%= t.getLieu() %></td>
                                    <td><%= t.getNomActivite() %></td>
                                    <td><%= t.getNombre() %></td>
                                    <td><%= t.getPrix() %></td>
                                    <td><%= t.getTotal() %></td>
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
                activiteTable.innerHTML = '<thead><tr><th>Dates debut</th><th>Dates fin</th><th>Destination</th><th>Bouquet</th><th>Lieu</th><th>Date activite</th><th>Lieu activite</th><th>Nom activite</th><th>Nombre de fois</th><th>Prix</th><th>Total montant activite</th></tr></thead><tbody></tbody>';

                // Parcourez toutes les activités et ajoutez celles qui correspondent aux entrées
                allActivities.forEach(function (activity) {
                    const activityDestination = activity.querySelector('td:nth-child(3)').textContent.toLowerCase();
                    const activityBouquet = activity.querySelector('td:nth-child(4)').textContent.toLowerCase();
                    const activityPrix = parseFloat(activity.querySelector('td:nth-child(10)').textContent);

                    if (
                        (enteredDestination === '' || activityDestination.includes(enteredDestination)) &&
                        (enteredBouquet === '' || activityBouquet.includes(enteredBouquet)) &&
                        (isNaN(enteredMinPrix) || activityPrix >= enteredMinPrix) &&
                        (isNaN(enteredMaxPrix) || activityPrix <= enteredMaxPrix)
                    ) {
                        activiteTable.querySelector('tbody').appendChild(activity.cloneNode(true));
                    }
                });
            }
        });
    </script>

</body>

</html>
