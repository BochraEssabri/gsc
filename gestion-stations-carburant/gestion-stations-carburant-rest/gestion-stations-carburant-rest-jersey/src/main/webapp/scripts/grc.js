$( document ).ready(function() {
	
  
  //// Partie intialisations ////
	
  // General	
    const END_POINT 			= URL_ROOT;
    const API_KEY 				= "5b578yg9yvi8sogirbvegoiufg9v9g579gviuiub8";
    const TYPE_CONTENU 			= "application/json";
    const TYPE_DONNEES			= "json";
    
    // Authentification pour utiliser les ws rest
    // Ref : https://stackoverflow.com/questions/34860814/basic-authentication-using-javascript
    const AUTHENTIFICATION_BASIC= "Basic ";
    
    
  // Messages
    const MSG_ACTION_IMPOSSIBLE = "L'action demandée est impossible, " 
								+ " merci de réviser la documentation"
								+ " pour l'utlisation de cette application."
    							+ "Note: Application en période d'essai, merci de "
								+ "contacter l'admin en cas de problème";
    
    const MSG_ERR_CACHE_STATION = "Impossible de trouver les stations dans "
    							+ "la mémoire cache, merci de recharger les données"
    							+ "Note: Application en période d'essai, merci de "
								+ "contacter l'admin en cas de problème";
    
    const MSG_INFO_PRIX_INDISPO = "<i>Le prix est indisponible pour cette date !</i>";

    // Operations:
  	const _GET	 	= "GET";
  	const _POST	 	= "POST";
  	const _PUT	 	= "PUT";
  	const _DELETE	= "DELETE";
  
  // sous adresses
	const CREATE 		= "create";
	const FIND	 		= "find";
	const GET_ALL		= "getAll";
	const UPDATE 		= "update";
	const DELETE 		= "delete";
	// Compatible seulement avec les données de type prix carburant
	/**
	 * Pour find prix station date.
	 */
	const Find_Pr_St_Dte= "findPrixStationDate";
	
  // Type Elements
	const CARBURANTS 		= "carburants/";
	const PRIX_CARBURANTS 	= "prixCarburants/";
	const STATIONS 			= "stations/";
	
  // Constantes sur les vues:
	const ID_LIBELLES				= "libs";
  
  // tags HTML
	/*
	 * Body d'une table
	 */ 
	const ID_CONTENU				= "contenu";
	// Liste déroulante
	const ID_CONTENU_LIST_STATIONS  = "contenuListeStations";
	const Liste_NOM_STATION			= "nom-station";
	const Liste_NOM_VILLE 			= "nom-ville";
	const CHAMP_DATE				= "date-prix";
	const PREF_CLASS_COLS 			= "column";
	const BOUTON_VALIDER			= "boutton-valider";
	
	//Section résultat recherche prix
	const SECTION_RESULTAT_RECHERCHE= "resultat-recherche-prix";
	const STATION_PRIX				= "station_prix";
	const VILLE_PRIX				= "ville_prix";
	const DATE_PRIX					= "date_prix";
	const PRIX_ESSENCE				= "prix_essence";
	const PRIX_GAZOLE				= "prix_gazole";
	
	
	
	// à rajouter le type et l'id : carburant, station ou prix puis l'id de la donnée
	const ID_ELEMENT				= "id_";
	
  
  // Ids constantes libelles
  // Un tableau qui contient les libellés de chaque élément ( carb, sta...)
	const CARBURANT			= "carburant";
	const STATION			= "station";
	const PRIX_CARBURANT	= "prix";
	// valeur de margin-top par défaut de la section chargement.
	const MARG_TOP_CHARGMNT = "27%";

	const TAILLE_COULONNES  = {
								"carburant":["30%", "70%"],
								"station" : ["30%", "25%", "55%"]
									
							  }
	
  // Constantes forms:
	/**
	 * Il faut remplacer $$element
	 * Exemple $$element = une station pour dire sélectionner une station.
	 */
	const SELECT_OPT_PAR_DEF = "<option id	=\"0\""
							 + " value =\"\" selected>"
							 + "   	  Sélectionner $$element"
							 + "</option>";
  // Classes conteneurs:
  // C'est  pour choisir le conteneur qui s'affiche.
	const CONT_LISTE			    = "container-list-grc";
	const CONT_FORM			    	= "container-form-grc";
	const CONT_ACTIF				= "activer-container"
    
  // Titres pages*
	const TITRE_LISTE_CARBURANTS 	= "Liste Des Carburants";
	const TITRE_PRIX_CARBURANTS 	= "Prix Des Carburants";
	
	
  // Références
    const ID_ESSENCE = 1;
    const ID_GAZOLE  = 2;
	
	//// Fonctions utils ////
	
	/**
	 * @param params Ex:{"param1":"p1", "param2" : "p2"}
	 * @return Ex: "?param1=p1&param2=p2"
	 */
	  function configurerUrlParam(params){
		  
			let urlParam = "";
			
			for ( var key in params) {
				urlParam += key + "=" + params[key] + "&";
			}
			
			urlParam = urlParam.substring(0, urlParam.length-1);
			
			urlParam = urlParam === "" ? urlParam : "?" + urlParam;
			
			return urlParam;
	  }
	  
	  /**
	   * Pour la validation d'une chaine de caractère si ça correpond à JSON.
	   * Ref: https://stackoverflow.com/questions/3710204/how-to-check-if-a-string-is-a-valid-json-string-in-javascript-without-using-try
	   */
	  function validerJson( source ){
		  
		  return    /^[\],:{}\s]*$/.test(source.replace(/\\["\\\/bfnrtu]/g, '@').
	                replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
	                replace(/(?:^|:|,)(?:\s*\[)+/g, ''))
	  }
	  
	  /**
	   * Vérifie si la réponse d'un ws est de type json
	   */
	  function validerReponseJson( typeDonnees ){
		  return typeDonnees.indexOf('json') > -1
	  }
	  
	  /**
	   * Pour les texts longs, on a besoin d'afficher une première partie
	   * selon un nombre de mots, après on peut développer une méthode
	   * pour afficher le reste du text selon une action(clic par exemple)
	   */
	  function limiterAffichageText(text, nombreDeMots){
		  var mots ="";
	  }
	  
	  /**
	   * Affiche du status chargement en cours
	   */
	  function activerChargementPage(){
		  
		  afficherSection("chargement");
	  }
	  
	  function terminerChargementPage(){
		  cacherSection("chargement");
	  }
	  
	  /**
	   * Déplace la section chargement version horizontalement
	   * à base du nombre de pixels @pourcentage.
	   */
	  function decalerChargement(pourcentage){
		  $(".chargement" ).css("margin-top", pourcentage);
	  }

	  /**
	   * Cache un element HTML.
	   */
	  function afficherSection( nomClasse ){
		  $("." + nomClasse ).css("display", "block");
	  }
	  
	  /**
	   * Affiche un element HTML caché.
	   */
	  function cacherSection( nomClasse ){
		  $("." + nomClasse ).css("display", "none");
	  }
	  
	  /**
	   * Pour choisir le conteneur qui s'affiche
	   * Ex: Liste, form...
	   */
	  function activerConteneur(classConteneur){
		  $("."+CONT_ACTIF).removeClass(CONT_ACTIF);
		  $("."+classConteneur).addClass(CONT_ACTIF);
	  }
	  
	  /**
	   * Le titre de la page
	   */
	  function changerTitre( titre ){
		  $(".titre").html(titre);
	  }
	  	  
	  /**
	   * @selecteur Ex: "td.column1"
	   */
	  function changerLongeurParSelecteur( selecteur, valeur ){
		  $( selecteur ).css({"width":valeur});
	  }
	  
	  /**
	   * Adapte la taille selon le contenu de la liste lignes 
	   * pour chaque élement
	   * @typeElementAffiche = id libelles : carburant, prix ou station.
	   */
	  function adapterTaillesColonnes( typeElementAffiche ){
		  const tailles = TAILLE_COULONNES[typeElementAffiche]
		  var colonne = 1;
		  
		  for(var key in tailles){
			  const TAILLE = tailles[key];
			  changerLongeurParSelecteur( "td.column" + colonne
					  					  + ", th.column" + colonne++,
					  					  TAILLE );
		  }
	  }
	  
	  /**
	   * Génère l'option par défaut dans un champ de type select
	   */
	  function genererOptionSelectionParDefaut( nomList ){
		  
		  return SELECT_OPT_PAR_DEF.replace("$$element", nomList);
	  }

	  /**
	   * cette fonction réactive l'option disabled d'un element html
	   * @selecteur comme .class ou #id
	   */
	  function desactiverChamp(selecteur){
		  $(selecteur)[0].disabled = true;
	  }
	  
	  /**
	   * cette fonction désactive l'option disabled d'un element html
	   * @selecteur comme .class ou #id
	   */
	  function activerChamp(selecteur){
		  $(selecteur)[0].disabled = false;
	  }
	  
	  function activerBouttonValidation(){
		  activerChamp("#" +BOUTON_VALIDER);
		  $("#" + BOUTON_VALIDER).removeClass("disabled");
	  }
	  
	  function desactiverBouttonValidation(){
		  desactiverChamp("#" +BOUTON_VALIDER);
		  $("#" + BOUTON_VALIDER).addClass("disabled");
	  }
	  
	  /**
	   * Anglais => français
	   */
	  function inverserDate(date){
		  if(date == null || date == "")
			  return date;
		  let parties = date.split("-");
		  let temp	 = parties[0];
		  parties[0] = parties[2];
		  parties[2] = temp;
		  
		  return parties.join("-");
	  }
	  
	  /**
	   * Adapte l'espace entre un décimal est une unité
	   * Ex: 9.4 dh ==> 9.4    dh
	   * 	 9.22 dh==> 9.22   dh
	   */
	  function adapterEspaceDecimalUnite(decimal){
		  decimal = decimal.toString();
		  const espace = "&nbsp;";
		  
		  let tailleNombreDecimal = decimal.split('.').length > 1 ? 4 - decimal.split('.')[1].length : 4;
		  
		  return decimal + espace.repeat(tailleNombreDecimal);
	  }
	  
	  function decimalToFrancais( decimal ){
		  return decimal.replace(".", ",");
	  }
	
    //// Définitions CLasses ////  
	  
		
	/**
	 * La requête pour la communication avec les services Rest
	 * Format exigé par la fonction $.ajax ( fonction de Jquery qui communique
	 * avec les services Rest
	 */
	class Requete{
		
		constructor(fonctionHttp,	// EX : POST
					sousAdresse,    // Une sous adresse. Ex: carburants
					operation,		// EX : getAll
					params 			// EX: id=1
					= {},    		// liste vide par défaut
					data 	 		// EX: { idCarburant : 1, ...}
					= {},
					success 		// fonction à executer si tout est OK.
					= traiterReponseRest,
					error			// fct si KO
					= taiterErreurRest,			
					typeContenu		// EX : "application/json"
					= TYPE_CONTENU, // val par défaut ( constante ) 
					typeData		// EX : "json"
					= TYPE_DONNEES	// val par défaut ( constante )
					){
			
			// Ex: {"a":1, "b":2} => ?a=1&b=2
			const paramUrl 		= configurerUrlParam(params);
			
			// Authentification pour utiliser les ws rest
			// Ref : https://stackoverflow.com/questions/34860814/basic-authentication-using-javascript
			this.headers  = {Authorization: AUTHENTIFICATION_BASIC + AUTHENTIFICATION};
			
			// Le type de la requête : POST, GET, PUT, DELETE 
			this.type			= fonctionHttp;
			
			// Ex: 		url 	= http://localhost:8080/carburants/find  ?id=1
			this.url 			= END_POINT + sousAdresse + operation + paramUrl;
			this.contentType	= typeContenu;
			
			// JSON par exemple
			this.dataType		= typeData;
			
			// JSON.stringify() génère le format json à partir d'un objet js, 
			// si écrit comme étant de text, le server ne l'accèpe pas
			// ref: https://stackoverflow.com/questions/5570747/jquery-posting-json
			//this.data			= JSON.stringify(data);
			
			this.success 		= success; 
			
			this.error	 		= error;

			
		}
	}
		
		
	/**
	 * Représente l'un des elements ( carburant, station ou prix ) concerné par une requête.
	 * 
	 * Un sorte de DTO ( data transfert object) coté front
	 * C'est le model pour MVC coté client
	 */ 
	class DataRest {
	 
		constructor( suffixUrlRest, typeData ) {
			this.typeData     = typeData; // Ex: carburant
			this.suffixUrlRest = suffixUrlRest;
	  	}
		
		/**
		 * Trouve un element de type de données this.typeData
		 * à base de son id passé en paramètre @id
		 * TODO Penser à mettre à jour la fonction callback
		 */
		findById( id ){
			// id : L'attribut de l'objet param
			// id : La valeur du paramètre de la fonction
			let param = {id:id}; 
			let requete = new Requete(
							_GET, 		  // fonction http  				 
							this.suffixUrlRest,	  // sousAdresse
							FIND,
							param
							// pour les autres paramètres, c'est la val par défaut.
						);
			
			envoyerRequeteRest(requete);
			
		}
		
		getAll( successHandler ){
			
			let requete = new Requete(
					_GET, 		  			  // fonction http  				 
					this.suffixUrlRest,	  // sousAdresse
					GET_ALL,
					{},			  			  // params
					{},						  // data
					successHandler			  // fonction à appeler si réponse OK
					// pour les autres paramètres, c'est la val par défaut.
			);
	
			envoyerRequeteRest(requete);
		}
		
		/**
		 * TODO Penser à mettre à jour la fonction callback
		 */
		create( data ){
			let requete = new Requete(
					_PUT, 		  // fonction http  				 
					this.suffixUrlRest,	  // sousAdresse
					CREATE,
					{},
					data
					// pour les autres paramètres, c'est la val par défaut.
			);
	
			envoyerRequeteRest(requete);
		}
		
		/**
		 * TODO Penser à mettre à jour la fonction callback
		 */
		update( data ){
			let requete = new Requete(
					_POST, 		  // fonction http  				 
					this.suffixUrlRest,	  // sousAdresse
					UPDATE,
					{},
					data
					// pour les autres paramètres, c'est la val par défaut.
			);
	
			envoyerRequeteRest(requete);
		}
		
		/**
		 * TODO Penser à mettre à jour la fonction callback
		 */
		delete( id ){
			let requete = new Requete(
					_DELETE, 	  // fonction http  				 
					this.suffixUrlRest,	  // sousAdresse
					DELETE
					// pour les autres paramètres, c'est la val par défaut.
			);
	
			envoyerRequeteRest(requete);
		}
		
		/**
		   * Pour la gestion de prix des deux carburants (Essence et Gazole)
		   * dans une station pour une date donnée.
		   * compatible seulement avec les données de type prix carburant
		   * ( this.typeData = PRIX_CARBURANT et this.suffixUrlRest = PRIX_CARBURANTS
		   */
		findPrixStationDate(idStation, date, successHandler){
			 if(! this.typeData 		=== PRIX_CARBURANT
				|| ! this.suffixUrlRest === PRIX_CARBURANTS){
				 	console.error(MSG_ACTION_IMPOSSIBLE);
			 }
			 
			 // On crée le paramètre à passé par l'url
			 let param = {idStation:idStation,
					 	  date:date}; 
			 
			 // on prépare la requête
			 let requete = new Requete(
							_GET, 		          // fonction http  				 
							this.suffixUrlRest,	  // sousAdresse
							Find_Pr_St_Dte,
							param,			  	  // params
							{},					  // data
							successHandler		  // fonction à appeler si réponse OK
							// pour les autres paramètres, c'est la val par défaut.
							// pour les autres paramètres, c'est la val par défaut.
			 );
			 
			 envoyerRequeteRest(requete);
			 
		}
	}


  
  //// Partie communication avec les WS REST ////
  
	/**
	 * Envoie de la requête
	 */
	function envoyerRequeteRest( requete ){
	  
	  $.ajax(requete);
	  
  }

  /**
   * Traitement d'une réponse d'un service Rest.
   */
  traiterReponseRest = function( resultat ){
	  console.log(resultat);
  }
  
  /**
   * Traitement d'une erreur générée lors de la communication avec un service Rest.
   */
  taiterErreurRest = function( informations ){
	  console.error(informations);
  }
  
  
  //// Partie vues ////
  
  /**
   * Responsable pour déclancher les communication et effectuer les affichages.
   * On peut l'appeler controller (dans l'architecture MVC coté client).
   * Les page forment la partie view ( dans MVC coté client).
   * 
   * @suffixUrlRest : l'url de gestion de type données traitées par le contrôleur.
   * @typeData
   */
  class Controller{
	  
	  constructor( suffixUrlRest, typeData ) {
		    
			this.suffixUrlRest = suffixUrlRest;
			this.typeData   	 = typeData; // Ex: carburant
			this.dataBean 	     = new DataRest(suffixUrlRest, typeData );
			
			// Redefinition de this pour les callback
			// car si non, si on appelle cette méthode par une autre classe,
			// this ne fera plus référence à la class Controller dans la méthode.
			this.afficherList			  	 = this.afficherList.bind(this);
			this.afficherPrixStationDate 	 = this.afficherPrixStationDate.bind(this);
			this.construireIhmListe 	 	 = this.construireIhmListe.bind(this);
			this.constuireListeVilles 	 	 = this.constuireListeVilles.bind(this);
			this.construireIhmPrixStationDate= this.construireIhmPrixStationDate.bind(this);
			this.preparerFormPrixStationDate = this.preparerFormPrixStationDate.bind(this);
	  }
	  
	  /**
	   * Compatible avec toute type de données.
	   */
	  afficherList( ){
		  
		  // Affichage chargement de données en cours ...
		  activerChargementPage();
		  cacherSection("contenu-page");
		  
		  this.mettreAJourLibelles();
		  
		  // Utilisation du web service rest.
		  this.dataBean.getAll(this.construireIhmListe);
	  }
	  
	  /**
	   * Pour la gestion de prix des deux carburants (Essence et Gazole)
	   * dans une station pour une date donnée.
	   * compatible seulement avec les données de type prix carburant
	   * ( this.typeData = PRIX_CARBURANT et this.suffixUrlRest = PRIX_CARBURANTS
	   */
	  afficherPrixStationDate(){
		  if(! this.typeData 		=== PRIX_CARBURANT
		  || ! this.suffixUrlRest === PRIX_CARBURANTS){
			  console.error(MSG_ACTION_IMPOSSIBLE);
		  }
		  
		  // Affichage chargement de données en cours ...
		  cacherSection("resultat-recherche-prix");
		  
		  // Pour adapter la section chargement avec le form
		  // liste de stations.
		  // Elle sera réadaptée à la fin de ce traitement
		  decalerChargement("5%");
		  activerChargementPage();
		  
		  
		  // Determination de l'id station sélectionnée
		  var data = cache.stationsCache;
		  if(data == null){
			  console.error(MSG_ERR_CACHE_STATION);
			  return;
		  }
		  
		  var infoStationSelectionnee = $("#"+Liste_NOM_VILLE).val().split("-");
		  var nomVille   = infoStationSelectionnee[0];
		  var nomStation = infoStationSelectionnee[1];
		  
		  var idStationSelectionnee = "";
		  
		  for(let idLigne in data){
			  let ligne = data[idLigne];
			  if( ligne.nomStation == nomStation
			   && ligne.ville   == nomVille){
				  idStationSelectionnee = ligne.idStation;
				  break;
			  }
		  }
		  

		  var date = $("#" + CHAMP_DATE).val();
		  
		  if(idStationSelectionnee == "" || date == null || date == ""){
			  console.error(MSG_ACTION_IMPOSSIBLE);
			  return;
		  }
		  
		  // Utilisation du web service rest.
		  this.dataBean.findPrixStationDate(idStationSelectionnee,
				  							date,
				  							this.construireIhmPrixStationDate);
		  
	  }
	  
	  /**
	   * Compataible avec les stations car elle intègre la liste des stations dans le form.
	   */
	  preparerFormPrixStationDate(){
		  if(! this.typeData 		=== STATION
			|| ! this.suffixUrlRest === STATIONS){
					  console.error(MSG_ACTION_IMPOSSIBLE);
		  }
		  
		// Affichage chargement de données en cours ...
		  activerChargementPage();
		  cacherSection("contenu-page");
		  
		// Utilisation du web service rest.
		  this.dataBean.getAll(this.construireIhmFormStations);
		
		  // Pour faire référence à la classe dans onChange
		  var thisClass = this;
	    // Changement de l'event handler quand on choisit une station
		  $("#"+Liste_NOM_STATION).change(function(){
			  thisClass.constuireListeVilles();
		  });
		// Changement de l'event handler quand on choisit une ville
		  $("#"+Liste_NOM_VILLE).change(function(){
			  if($("#"+Liste_NOM_VILLE).val() != ""){
				  activerBouttonValidation();
				  $("#" + BOUTON_VALIDER).click(prixCarburantsController.afficherPrixStationDate);
			  }
			  else{
				  desactiverBouttonValidation();
			  }
		  });
		  
	  }
	  
	  /**
	   * Compataible avec les stations car elle intègre la liste des stations dans le form.
	   */
	  construireIhmFormStations(data, status, xhr){
		  
		  if(! this.typeData 		=== STATION
			|| ! this.suffixUrlRest === STATIONS){
				  console.error(MSG_ACTION_IMPOSSIBLE);
				  return;
		  }
		  
		  var typeDonnes = xhr.getResponseHeader("content-type") || "";
		  
		  
		  if(!validerReponseJson(typeDonnes)){
			  console.error("Une erreur s'est produite "
					  	    + "lors de la communication avec le serveur"
					  		+ "l'application est en période d'essaie. "
					  		+ "merci de nous contacter et nous communiquer "
					  		+ "l'erreur accompagnée à ce message");
			  return;
		  }
		  
		  if(data.length == 0){
			  console.log("Aucun élement n'a été renvoyé par le serveur");
			  return;
		  }
		  
		  let donneesIHM     = genererOptionSelectionParDefaut("une station");
		  
		  for(let idLigne in data){
			  
			  let ligne 	 =   data[idLigne];
			  let idStation  = ligne.idStation;
			  let nomStation = ligne.nomStation;
			  if(!donneesIHM.includes(nomStation)){
				  let option	 = "<option id =\""+idStation+"\""
				  				 + " value=\"nom-sta-"+idStation+"\">"
				  				 + nomStation
				  				 + "</option>";
				  donneesIHM	 += option; 
			  }
			  
			  
		  }
		  // référence vers l'élément HTML qui contient les données
		  let tagContenu = $("#"+Liste_NOM_STATION);
		  
		  // Ajout de données dans la page
		  tagContenu.html( donneesIHM);
		  
		  activerConteneur(CONT_FORM);
		  
		  cache.sauvegarderStations(data);
		  
		  afficherSection("contenu-page")
		  terminerChargementPage();
		  
	  }
	  
	  /**
	   * Compataible avec les stations car elle intègre la liste des villes des stations dans le form.
	   */
	  constuireListeVilles(){
		  var data = cache.stationsCache;
		  
		  if(data == null){
			  console.log(MSG_ERR_CACHE_STATION);
			  return;
		  }

		  if($("#"+Liste_NOM_STATION).val() == ""){
			  $("#"+Liste_NOM_VILLE).val("");
			  desactiverChamp("#"+Liste_NOM_VILLE);
			  desactiverBouttonValidation();
			  return;
		  } 
		  
		  let idStationSelectionnee = $("#"+Liste_NOM_STATION).val().split("nom-sta-")[1];
		  
		  let nomStationSelectionnee = "";
		  
		  for(let idLigne in data){
			  if(data[idLigne].idStation == idStationSelectionnee){
				  nomStationSelectionnee = data[idLigne].nomStation;
				  break;
			  }
		  }
		  let donneesIHM     = genererOptionSelectionParDefaut("une ville");
		  
		  for(let idLigne in data){
			  
			  let ligne 	 =   data[idLigne];
			  let nomStation  = ligne.nomStation;
			  let ville		 = ligne.ville;
			  
			  
			  
			  
			  // Pour choisir garder les villes de la station sélectionnée
			  // non encore ajoutées.
			  if(   nomStation == nomStationSelectionnee
				 && !donneesIHM.includes(ville) ){
				  let option	 = "<option id =\"ville-"+ville+"\""
					 + " value=\""+ville+"-"+nomStation+"\">"
					 + ville
					 + "</option>";
				  
				  donneesIHM	 += option; 
			  }
			  
			 
			  
		  }
		  // référence vers l'élément HTML qui contient les données
		  let tagContenu = $("#"+Liste_NOM_VILLE);
		  
		  // Ajout de données dans la page
		  tagContenu.html(donneesIHM);
		  
		  /**
		   * On active la modification dans la liste des villes
		   */
		  activerChamp(tagContenu);
	  }
	  
	  /**
	   * Pour la gestion de prix des deux carburants (Essence et Gazole)
	   * dans une station pour une date donnée.
	   * compatible seulement avec les données de type prix carburant.
	   * 
	   * 
	   * ( this.typeData = PRIX_CARBURANT et this.suffixUrlRest = PRIX_CARBURANTS
	   * <tr id ="id_carburant0">
	   * 	<td class="column1 id_carburant">1</td>
	   * 	<td class="column2 nom_carburant">Essence</td>
	   * 	<td class="column3 description">description Essence</td>
	   * </tr>
	   * 
	   * @data, @status et @xhr sont des paramètres injectés automatiquement
	   * après réception de la réponse Http par l'objet $.ajax.
	   * 
	   * */
	  construireIhmPrixStationDate(data, status, xhr){
		  
		  if(  ! this.typeData 		=== PRIX_CARBURANT
			|| ! this.suffixUrlRest === PRIX_CARBURANTS){
				console.error(MSG_ACTION_IMPOSSIBLE);
		  }
		  
		  var typeDonnes = xhr.getResponseHeader("content-type") || "";
		  
		  
		  if(!validerReponseJson(typeDonnes)){
			  console.error("Une erreur s'est produite "
					  	    + "lors de la communication avec le serveur"
					  		+ "l'application est en période d'essaie. "
					  		+ "merci de nous contacter et nous communiquer "
					  		+ "l'erreur accompagnée à ce message");
			  return;
		  }
		  
		  let tagHtmlPrixEssence = $("#" + PRIX_ESSENCE);
		  let tagHtmlPrixGazole  = $("#" + PRIX_GAZOLE);
		  
		  
		  if(data.length == 0){
			  console.log("Aucun élement n'a été renvoyé par le serveur");
			  // ici on traite les cas d'absence de prix
			 
			  tagHtmlPrixEssence.html(MSG_INFO_PRIX_INDISPO);
			  
			  tagHtmlPrixGazole.html(MSG_INFO_PRIX_INDISPO);
			  
			  $("#" + VILLE_PRIX).html($("#"+Liste_NOM_VILLE).val().split("-")[0]);
			  $("#" + DATE_PRIX).html(inverserDate($("#" + CHAMP_DATE).val()));
			  
			  terminerChargementPage();
			  decalerChargement(MARG_TOP_CHARGMNT);
			  afficherSection("resultat-recherche-prix");
			  
			  return;
		  }
		  
		  let donneesIHM    = "";


		  $("#" + STATION_PRIX).html(data[0].station.nomStation);
		  $("#" + VILLE_PRIX).html(data[0].station.ville);
		  $("#" + DATE_PRIX).html(inverserDate(data[0].date));
		  
		  
		  for(let id in data){
		  	 let ligne = data[id];
		  	 let idCarburant = ligne.carburant.idCarburant;
		  	 let prix = ligne.prix;
		  	 if(idCarburant == ID_ESSENCE){
		  		tagHtmlPrixEssence.html(
		  				decimalToFrancais(adapterEspaceDecimalUnite(prix))+"dh");
		        
             }
             else{
            	 tagHtmlPrixGazole.html(
            			 decimalToFrancais(adapterEspaceDecimalUnite(prix))+"dh");
             }
		  	 
		  	 
		  }
		  
		  // ici on traite les cas d'absence de prix
		  if(tagHtmlPrixEssence.html() == ""){
			  tagHtmlPrixEssence.html(MSG_INFO_PRIX_INDISPO);
		  }
		  if(tagHtmlPrixGazole.html() == ""){
			  tagHtmlPrixGazole.html(MSG_INFO_PRIX_INDISPO);
		  }
		  
		  
		  
		  terminerChargementPage();
		  decalerChargement(MARG_TOP_CHARGMNT);
		  afficherSection("resultat-recherche-prix");
		  
	  }
	  
	  /**
	   * Pour afficher une liste d'élements de nature @id en utilisant les données @data.
	   * <tr id ="id_carburant0">
	   * 	<td class="column1 id_carburant">1</td>
	   * 	<td class="column2 nom_carburant">Essence</td>
	   * 	<td class="column3 description">description Essence</td>
	   * </tr>
	   * 
	   * @data, @status et @xhr sont des paramètres injectés automatiquement
	   * après réception de la réponse Http par l'objet $.ajax.
	   * 
	   * */
	  construireIhmListe(data, status, xhr){
		  
		  var typeDonnes = xhr.getResponseHeader("content-type") || "";
		  
		  
		  if(!validerReponseJson(typeDonnes)){
			  console.error("Une erreur s'est produite "
					  	    + "lors de la communication avec le serveur"
					  		+ "l'application est en période d'essaie. "
					  		+ "merci de nous contacter et nous communiquer "
					  		+ "l'erreur accompagnée à ce message");
			  return;
		  }
		  
		  if(data.length == 0){
			  console.log("Aucun élement n'a été renvoyé par le serveur");
			  return;
		  }
		  
		  let donneesIHM    = "";
		  
		  // On recupère les ids
		  let ids = Object.keys(libelles[this.typeData]);
		  
		  for(let idLigne in data){
			  
			  let ligne =   data[idLigne];
			  
			  donneesIHM += "<tr id =\"" 
				  		 +  ID_ELEMENT 
				  		 +  this.typeData 
				  		 +  ligne[ids[0]] 
			  			 +  "\">";
			  
			  let numeroColonne = 1;
			  
			  // On commence par 1 pour éviter le première élement qui correspond
			  // à la colonne id de chaque ligne.
			  for(var idLibelle = 1; idLibelle < ids.length; idLibelle++){
				  
				  let idColonne = ids[idLibelle];
				  
				  donneesIHM  += "<td class=\"" 
					  		  +  PREF_CLASS_COLS 
					  		  +  (numeroColonne++) 
					  		  +  "\">";
				  
				  
				  
				  if( ligne[idColonne] !== undefined ){
				  
					  donneesIHM +=  ligne[idColonne];
				  }
				  
				  donneesIHM += "</td>"
			  }
			  donneesIHM += "</tr>";
		  }
		  
		  // référence vers l'élément HTML qui contient les données
		  let tagContenu = $("#"+ID_CONTENU);
		  
		  // Ajout de données dans la page
		  tagContenu.html(donneesIHM);
		  
		  adapterTaillesColonnes(this.typeData);
		  
		  activerConteneur(CONT_LISTE);
		  
		  afficherSection("contenu-page")
		  terminerChargementPage();
	  }
	  
	  // Ordre déjà respecté grace à la liste libelles pré-définie
	  // dans la page JSP.
	  // typeData représente l'id de chaque element dans la lise libelles.
	  mettreAJourLibelles(){
		  const data = libelles[this.typeData];
		  var resultat ="";
		  var numeroCol= 1;
		  
		  // On ignore le premier libellé car ça correspond à l'id.
		  
		  let ids = Object.keys(data);
		  
		  for ( var key = 1; key < ids.length; key++) {
			  
			  resultat += "<th class=\"" 
				  		+ PREF_CLASS_COLS
				  		+ (numeroCol++)
				  		+ "\">" 
				  		+ data[ids[key]]
				  		+ "</th>";
		  }
		  
		  $("#" + ID_LIBELLES).html(resultat);
	  }
	  
  }
  
  class GestionEvenements{
	  
	  constructor(){
		  // Redefinition de this pour les callback
		  // car si non, si on appelle cette méthode par une autre classe,
		  // this ne fera plus référence à la class Controller dans la méthode.
		  // ici on utilise la méthode traditionnelle car bind ne fonctionne pas
		  // avec les click de jquery.
		  var thisClass = this;
		  //this.afficherAcceuil 			 = this.afficherAcceuil.bind(this);
		  //this.afficherListeCarburants 	 = this.afficherListeCarburants.bind(this);
			
		  // Déclaration des événements clics.
		  $("#listCarburants").click( function(){
			  
			  $(".active").removeClass("active");
			  $("#listCarburants").addClass("active");

			  thisClass.afficherListeCarburants();
		  });

		  $("#acceuil").click( function(){
			  $(".active").removeClass("active");
			  $("#acceuil").addClass("active");
			  thisClass.afficherAcceuil();
		  });
	  }
	  
	  afficherAcceuil(){
		  changerTitre(TITRE_PRIX_CARBURANTS);
		  var stationsController = new Controller(STATIONS, STATION);
		  stationsController.preparerFormPrixStationDate();
		  
	  }
	  
	  afficherListeCarburants(){
		  changerTitre(TITRE_LISTE_CARBURANTS);
		  var carburantsController = new Controller(CARBURANTS, CARBURANT);
		  carburantsController.afficherList(); 
	  }
	  
  }
 
  /**
	 * Pour sauvegarder temporairement les stations récupérées du ws
	 * rest et eviter de réappeler à chaque fois
	 */
  class Cache{
	constructor()
	{
			
		  this.stationsCache	 = [];
		  
		  this.sauvegarderStations 			 = this.sauvegarderStations.bind(this);
	}
	sauvegarderStations( stations ){
		
		  this.stationsCache = stations;
	}
  }

  var cache	= new Cache();
  var gestionEvenements = new GestionEvenements();
  gestionEvenements.afficherAcceuil();
  
  var prixCarburantsController = new Controller(PRIX_CARBURANTS, PRIX_CARBURANT);
  
  
  
  
  
  // tests
  var carburant = new DataRest(CARBURANTS); 
  //carburant.update({"idCarburant":5, "nomCarburant":"sol555", "description":"solair2"});
  
  
  
  //mettreAJourLibelles(libelles[CARBURANT]);
  
  
  
//$.ajax({
//url: endpoint,
//contentType: "application/json",
//dataType: 'json',
//success: traiterReponseRest
//})
});
