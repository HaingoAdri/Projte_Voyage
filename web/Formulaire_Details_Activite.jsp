<%-- 
    Document   : Formulaire_Details_Activite
    Created on : 19 déc. 2023, 12:07:56
    Author     : razafinjatovo
--%>

<%@page import="model.Activite"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Vector<Activite> allactivity = (Vector<Activite>) request.getAttribute("allactivity"); 
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

    <title>Formulaire details activite</title>

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
            <h1>Insertion de details voyage :</h1>
        </div>
        <p></p>
        <div class="card_body">
            <div class="row marketing">
                <form action="" method="post">
                    <div class="table-responsive">
                        <table class="table table-striped">
                          <thead>
                            <tr>
                              <th>Date</th>
                              <th>Heure debut</th>
                              <th>Heure fin</th>
                              <th>Activite</th>
                              <th>Lieu</th>
                              <th>Nombre</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                               <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
                              <td><input type="date" class="form-control" name="dateActivite"></td>
                              <td><input type="time" class="form-control" name="heuredebut"></td>
                              <td><input type="time" class="form-control" name="heurefin"></td>
                              <td><select name="activite" id="" class="form-control">
                                <%for(Activite t : allactivity){%>
                                <option value="<%=t.getId()%>"><%=t.getNom()%></option>
                                <%}%>
                              </select></td>
                              <td><input type="text" class="form-control" name="lieuactivite"></td>
                               <td><input type="number" class="form-control" name="nbrfois"></td>
                            </tr>
                        </tbody>
                        </table>
                    </div>
                    <input type="submit" class="btn btn-success" value="Inserer">
                </form>
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
  </body>
</html>

