<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!--  On utilise les jstl de type "prefixe c:" pour pouvoir ajouter des conditions au code. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>GSC | Gestion Station Carburant</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript">
		const libelles 			= ${it.LIBELLES};
		
		// Authentification pour utiliser les ws rest
		// Ref : https://stackoverflow.com/questions/34860814/basic-authentication-using-javascript
		const AUTHENTIFICATION 	= "${it.AUTHENTIFICATION}";
		
		// Ceci génère l'url de l'application qu'on va utiliser
		const URL_ROOT 		   	=  "${it.URL_ROOT}";
	</script>
	<!--===============================================================================================-->	
		<link rel="icon" type="image/png" href="${it.URL_ROOT}template-bootstrap/images/icons/favicon.ico"/>
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css" integrity="sha512-oc9+XSs1H243/FRN9Rw62Fn8EtxjEYWHXRvjS43YtueEewbS6ObfXcJNyohjHqVKFPoXXUxwc+q1K7Dee6vv9g==" crossorigin="anonymous" />
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/vendor/animate/animate.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/vendor/select2/select2.min.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/vendor/perfect-scrollbar/perfect-scrollbar.css">
	<!--===============================================================================================-->
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/css/util.css">
		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}template-bootstrap/css/main.css">
	<!--===============================================================================================-->

		<link rel="stylesheet" type="text/css" href="${it.URL_ROOT}styles/grc.css" />
		
		<meta charset="UTF-8">
	</head>
	<body>
	
	<!--  header -->
		<nav class="navbar navbar-expand navbar-dark bg-dark">
	      <a class="navbar-brand" href="#">GSC <sub>Gestion station et Prix Carburants</sub> </a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
		  <c:if test="${it.AUTHENTIFICATION != null}">
		      <div class="collapse navbar-collapse" id="navbarsExample02">
		        <ul class="navbar-nav mr-auto">
		          <li class="nav-item active">
		            <a class="nav-link" id="acceuil" href="#">Accueil <span class="sr-only">(current)</span></a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link " id="listCarburants" href="#">Liste des carburants</a>
		          </li>
		        </ul>
		        
		        <form class="form-inline my-2 my-md-0">
			        <c:if test="${it.UTILISATEUR != null}">
			        	 <div class= "nom-utilisateur">${it.UTILISATEUR}&nbsp;</div>
			          	 <div class= "lien-se-deconnecter">
			          	 	  <a href = "${it.URL_ROOT}deconnexion"> se déconnecter</a>
			          	 </div>
				         
			        </c:if>
		         
		        </form>
		      </div>
		   </c:if>
	    </nav>
	    
	    <div class="titre">
	    	Connexion
	    </div>
	    <div class="limiter contenu-page">
	    	<c:if test="${it.AUTHENTIFICATION != null}">
			
				<div class="container-table100 container-list-grc">
					<div class="wrap-table100">
						<div class="table100">
							<div class="theader">
								<table>
									<thead>
										<tr class="table100-head" id="libs">
											
										</tr>
									</thead>
									</table>
									</div>
							<div class="tbody">
								<table>
									<tbody id="contenu">
	
										
	
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
				</div>
				<div class="container-form-grc">
					<div id="contenuListeStations" >
						<form >
							<div class="form-row">
								<div class="col-3">
								    <label for="nom-station">Station</label>
								    <select class	="form-control" 
								    		id   	="nom-station">
								    <!--
								    	Auto généré par JS construireIhmFormStations()
								    	Exemple : 
								    	  <option id	="0" 
								      		  value ="" selected>
								      	  Sélectionner une station
									      </option>
									      <option id="1" value="1">Total</option>
								    
								     -->
								      
								    </select>
								</div>
								<div class="col-3">
								    <label for="nom-ville">Ville</label>
								    <select class	="form-control" 
								    		id		="nom-ville"
								    		disabled> <!-- Sera activé après sélection d'un nom de station -->
							    		<!--
								    	 Auto généré par JS construireIhmFormStations()
									     Exemple : 
									      <option id    ="0" 
									      		  value ="" selected>
									      	   Sélectionner une ville
									      </option>
									      <option id="1" value="1">Tanger</option>-
									     -->
								     </select>
								</div>
								<div class="col-2">
									<label for="date-prix" class="col-1 col-form-label">Date</label>
									
									<div >
										<input  class	= "form-control date-prix" 
												type	= "date" 
												id		= "date-prix"
												value	= "${it.date}">
									</div>
								</div>
								<div class="col-3">
								    <button type="button" id="boutton-valider" class="btn btn-primary disabled">Valider</button>
								</div>
							</div>	
						</form>
					</div>
					<div id="resultat-recherche-prix" class="resultat-recherche-prix">
						<!-- ref:https://stackoverflow.com/questions/16852484/use-fieldset-legend-with-bootstrap -->
						<fieldset class="resultat-border">
							<legend class="resultat-border">Résultat</legend>
							<div class="">
								<span id="titre_resultat_prix">
									Prix carburants à la station 
										<span id="station_prix">PETRPM</span>, 
										<span id="ville_prix"></span>, 
										le <span id="date_prix"></span>:
								</span>
								
							</div>
							<div class="control-group row">
								<label class="font-weight-bold control-label col-2">Essence:
								</label>
								<div class="prix controls bootstrap-timepicker col-8">
									<span id="prix_essence"></span>
								</div>
							</div>
							<div class="control-group row">
								<label class="font-weight-bold control-label col-2">Gazole:
								</label>
								<div class="prix controls bootstrap-timepicker col-8">
									<span id="prix_gazole"></span>
								</div>
							</div>
						</fieldset>
					</div>
				</div>
			</c:if>
			<c:if test="${it.AUTHENTIFICATION == null}">
				<div class="container-form-grc form-connexion-utilisateur">
					<form method= "POST"
						  action= "${it.URL_ROOT}connexion"
						  name=	  "connexion">
						<div class="form-row ">
							<div class="col-3">
							    <label for="nom-station">Nom utilisateur</label>
							    <input  class	= "form-control" 
							    		id   	= "nom-utilisateur"
							    		name	= "nom-utilisateur"
							    		type	= "text" />
							</div>
							<div class="col-3">
							    <label for="nom-ville">Mot de passe</label>
							    <input  class	= "form-control" 
							    		id   	= "mot-de-passe"
							    		name	= "mot-de-passe"
							    		type	= "password" />
							</div>
							<div class="col-3">
							    <button type="button" id="boutton-valider" class="btn btn-primary disabled">Valider</button>
							</div>
						</div>	
					</form>
					<c:if test="${it.AUTH_KO != null}">
						<div id="AUTH_KO">${it.AUTH_KO}</div>
					</c:if>
				</div>
			</c:if>
		</div>
		
		<!-- En attente de chargement de données -->
		<div class="chargement">
			<div class="spinner-border" role="status">
  				<span class="sr-only">
      				
				</span>

			</div>
			<br>
			<div class="text">
				Chargement ...
			</div>
			
		</div>

		<!--===============================================================================================-->	
		<script src="${it.URL_ROOT}template-bootstrap/vendor/jquery/jquery-3.2.1.min.js"></script>
		<!--===============================================================================================-->
		<script src="${it.URL_ROOT}template-bootstrap/vendor/bootstrap/js/popper.js"></script>
		<script src="${it.URL_ROOT}template-bootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
		<!--===============================================================================================-->
		<script src="${it.URL_ROOT}template-bootstrap/vendor/select2/select2.min.js"></script>
		<!--===============================================================================================-->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/js/bootstrap.min.js" integrity="sha512-8qmis31OQi6hIRgvkht0s6mCOittjMa9GMqtK9hes5iEQBQE/Ca6yGE5FsW36vyipGoWQswBj/QBm2JR086Rkw==" crossorigin="anonymous"></script>
		
		<c:if test="${it.AUTHENTIFICATION != null}">
			<script type="text/javascript" src="${it.URL_ROOT}scripts/grc.js?v=1"></script>
		</c:if>
		<c:if test="${it.AUTHENTIFICATION == null}">
			<script type="text/javascript" src="${it.URL_ROOT}scripts/grc-connexion.js?v=1"></script>
		</c:if>
		
	</body>
</html>