<%-- 
    Document   : Forulaire_Voyage
    Created on : 19 déc. 2023, 12:06:50
    Author     : razafinjatovo
--%>

<%@page import="model.Poste"%>
<%@page import="model.Bouquet"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Vector<Poste> allBouquet = (Vector<Poste>) request.getAttribute("poste");%>
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
                <form action="Employer" method="post">
                    <div class="row">
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Date embauche :</span>
                        <input type="date" class="form-control" placeholder="Username" aria-describedby="sizing-addon2" name="date">
                    </div>
                    <p></p>
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Nom :</span>
                        <input type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon2" name="nom">
                    </div>
                    <p></p>
                    
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Prenom :</span>
                        <input type="text" class="form-control" placeholder="Username" aria-describedby="sizing-addon2" name="prenom">
                    </div>
                    <p></p>
                    
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Salaire :</span>
                        <input type="number" class="form-control" placeholder="Salaire" aria-describedby="sizing-addon2" name="salaire">
                    </div>
                    <p></p>
                    
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Poste :</span>
                        <select name="poste" aria-describedby="sizing-addon2" class="form-control" placeholder="Username" aria-describedby="sizing-addon2" name="datefin">
                            
                                <option value="1">Ouvrier</option>
                            
                        </select>
                    </div>
                    <p></p>

                    
                    <input type="submit" class="btn btn-success" value="Suivant">
                  </div><!-- /.row -->
                </form>
              </div>
        </div>
      </div>
      
      <hr>
      <footer class="footer">
        <p>&copy; 20269</p>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

