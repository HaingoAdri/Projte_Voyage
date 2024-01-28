<%-- 
    Document   : Forulaire_Voyage
    Created on : 19 déc. 2023, 12:06:50
    Author     : razafinjatovo
--%>

<%@page import="model.Vente"%>
<%@page import="model.Voyage"%>
<%@page import="model.Genre"%>
<%@page import="java.util.List"%>
<%@page import="model.TrancheAge"%>
<%@page import="model.Destination"%>
<%@page import="model.Bouquet"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Vector<Genre> genre = (Vector<Genre>) request.getAttribute("genre");
    Vector<Voyage> voyage = (Vector<Voyage>) request.getAttribute("voyage");
    List<Vente> vente = (List<Vente>) request.getAttribute("vente");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Formulaire</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron-narrow.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
            <h1>Insertion de Vente :</h1>
        </div>
        <p></p>
        <div class="card_body">
            <div class="row marketing">
                <form action="VenteServlet" method="post">
                    
                    <table id="venteTable" class="table">
                        <thead>
                            <th>Date</th>
                            <th>Client</th>
                            <th>Genre</th>
                            <th>Voyage</th>
                        </thead>
                        
                        <tbody>
                            <td>
                                <input type="date" name="date" class="form-control">
                            </td>
                            <td>
                                <input type="text" name="nom"class="form-control">
                            </td>
                            <td>
                                <select name="genre" class="form-control">
                                    <%for (Genre g : genre){%>
                                    <option value="<%=g.getId()%>"><%=g.getNom()%></option>
                                    <%}%>
                                </select>
                            </td>
                            <td>
                                <select name="voyage"class="form-control">
                                    <%for (Voyage v : voyage){%>
                                    <option value="<%=v.getId()%>"><%=v.getId()%></option>
                                    <%}%>
                                </select>
                            </td>
                        </tbody>
                        
                    </table>
                    
                    <input type="submit" class="btn btn-success" value="Suivant">
                    </div><!-- /.row -->
                </form>
                <p></p>
                <button id="ajouterLigneBtn">Ajouter ligne</button>
                
                <p></p>
                <h1>Statistique de vente</h1>
                <p></p>
                <table class="table">
                    <thead>
                        <th>Genre</th>
                        <th>Voyage</th>
                        <th>Nombre</th>
                        <th>Pourcentage</th>
                    </thead>
                    <tbody>
                        <% for (Vente t : vente){ %>
                        <tr>
                            <td><%=t.getNom()%></td>
                            <td><%=t.getIdvoyage()%></td>
                            <td><%=t.getIdclient()%></td>
                            <td><%=t.getPourcentage()%> %</td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                    
                <p></p>
                <h1>Graphe vente</h1>
                <canvas id="myChart" width="400" height="200"></canvas>
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
  document.addEventListener('DOMContentLoaded', function() {
    var ajouterLigneBtn = document.getElementById('ajouterLigneBtn');
    var venteTable = document.getElementById('venteTable');

    ajouterLigneBtn.addEventListener('click', function() {
      var newRow = venteTable.insertRow(-1);

      var cell1 = newRow.insertCell(0);
      var cell2 = newRow.insertCell(1);
      var cell3 = newRow.insertCell(2);
      var cell4 = newRow.insertCell(3);

      cell1.innerHTML = '<input type="date" name="date" class="form-control">';
      cell2.innerHTML = '<input type="text" name="nom" class="form-control">';
      cell3.innerHTML = '<select name="genre" class="form-control"><option value=""></option> </select>';
      cell4.innerHTML = '<select name="voyage" class="form-control"> <option value=""></option> </select>';
    });

    // Récupérer les données des ventes depuis la page HTML
    var ventes = [];
    <% for (Vente t : vente) { %>
      ventes.push({
        genre: '<%= t.getNom() %>',
        voyage: '<%= t.getIdvoyage() %>',
        pourcentage: <%= t.getPourcentage() %>
      });
    <% } %>

    // Préparer les données pour le graphe
    var data = {};
    ventes.forEach(function(vente) {
      if (!data[vente.voyage]) {
        data[vente.voyage] = {
          hommes: 0,
          femmes: 0
        };
      }
      if (vente.genre === 'homme') {
        data[vente.voyage].hommes += vente.pourcentage;
      } else if (vente.genre === 'femme') {
        data[vente.voyage].femmes += vente.pourcentage;
      }
    });

    // Créer les labels et les datasets pour le graphe
    var labels = Object.keys(data);
    var datasets = [];
    datasets.push({
      label: 'Hommes',
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      data: labels.map(function(voyage) {
        return data[voyage].hommes;
      })
    });
    datasets.push({
      label: 'Femmes',
      backgroundColor: 'rgba(255, 99, 132, 0.2)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1,
      data: labels.map(function(voyage) {
        return data[voyage].femmes;
      })
    });

    // Dessiner le graphe
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: datasets
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true 
            }
          }]
        }
      }
    });
  });
</script>

  </body>
</html>
