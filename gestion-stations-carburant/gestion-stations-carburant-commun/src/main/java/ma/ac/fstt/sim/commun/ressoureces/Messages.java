package ma.ac.fstt.sim.commun.ressoureces;

public class Messages {
	
	/*****************
	 * Base de données
	 ******************/
	public static String MSG_TESANSACTION_COMMENCEE 	= "Execution d'une requête en base de données...";

	public static String MSG_TESANSACTION_EXECUTEE 		= "Requête en base de données executée !";

	public static String MSG_TESANSACTION_OK 			= "Execution d'une requête en base de données OK !";

	public static String ERR_TESANSACTION_KO 			= "Une erreur lors de l'execution d'une requête en base de données.";
	
	/**
	 * Il faut ajouter l'element concerné après les deux points.
	 */
	public static String ERR_OPERATION_INDISPONIBLE 	= "Cette opération est indisponible pour : ";

	
	/******************
	 * WS SOAP
	 ******************/
	
	//// Messages ////
	

	public static final String MSG_DEMARRAGE_APPLI 		= "Demarrage de l'application...";
	
	public static final String MSG_INVOCATION_BD	    = "Invocation de la BD en cours...";
	
	public static final String MSG_SERVICE_OK 			= "Les services sont disponibles!";
	
	public static final String MSG_PUBLICATION_SERVICES = "Publication de services SOAP...";
	
	public static final String MSG_PUBLICATION 			= "Publication de ";	
	
	public static final String ERR_ACCES_NON_AUTORISE	= "Tentative d'accès non autorisé!!";
	
	// SOAP Carburant
	
	public static final String MSG_CARBURANT_RCHRCH		= "Recherche d'un carburant: ";	
	
	public static final String MSG_CARBURANT_ENRGSTRMT	= "Enregistrement d'un carburant: ";	
	
	public static final String MSG_CARBURANT_SUPPRSSN	= "Supression d'un carburant: ";	
	
	public static final String MSG_CARBURANT_MODIF		= "Modification d'un carburant: ";	
	
	public static final String MSG_CARBURANT_LIST		= "Récupration de la liste des carburants... ";	
	
	public static final String MSG_CARBURANT_LIST_VIDE	= "Aucun Carburant n'a été trouvé ... ";	
	
	public static final String MSG_CARBURANT_TROUVE		= "Carburant trouvé: ";
	
	public static final String MSG_CARBURANT_ENREGISTRE	= "Carburant enregistré : ";	
	
	public static final String MSG_CARBURANT_SUPPRM		= "Carburant supprimé : ";	
	
	public static final String MSG_CARBURANT_MODIFIE	= "Carburant modifié : ";	
	
	
	// SOAP Station
	public static final String MSG_STATION_MODIF 		= "Modification d'une station: ";

	public static final String MSG_STATION_RCHRCH 		= "Recherche d'une station: ";	

	public static final String MSG_STATION_TROUVE 		= "Station trouvée: ";	

	public static final String MSG_STATION_ENRGSTRMT 	= "Enregistrement d'une station: ";

	public static final String MSG_STATION_ENREGISTRE 	= "Station enregistrée : ";

	public static final String MSG_STATION_SUPPRSSN 	= "Supression d'une station: ";

	public static final String MSG_STATION_LIST 		= "Récupration de la liste des stations... ";	

	public static final String MSG_STATION_LIST_VIDE 	= "Aucune Station n'a été trouvée ... ";	
	
	public static final String MSG_STATION_SUPPRM		= "Station supprimée : ";	
	
	public static final String MSG_STATION_MODIFIE		= "Station modifiée : ";	
	
	
	// SOAP Prix Carburant
	public static final String MSG_PRIX_RCHRCH 			= "Recherche de prix de carburant: ";	

	public static final String MSG_PRIX_TROUVE 			= "Prix trouvé: ";

	public static final String MSG_PRIX_ENRGSTRMT 		= "Enregistrement d'un de carburant: ";

	public static final String MSG_PRIX_ENREGISTRE 		= "Prix carburant enregistré : ";

	public static final String MSG_PRIX_MODIF 			= "Modification du prix: ";

	public static final String MSG_PRIX_MODIFIE 		= "Prix carburant modifié : ";

	public static final String MSG_PRIX_SUPPRSSN 		= "Supression du prix d'un carburant: ";

	public static final String MSG_PRIX_SUPPRM 			= "Prix carburant supprimé : ";	

	public static final String MSG_PRIX_LIST 			= "Récupration de la liste des prix de carburants... ";	

	public static final String MSG_PRIX_LIST_VIDE 		= "Aucun prix carburant n'a été trouvé ... ";

	//// Erreurs ////
	
	public static final String ERR_PARAM				= "Erreur: Paramètre Incorrecte";	
	
	// SOAP Carburant

	public static final String ERR_CARBURANT_INTROUVABLE= "Carburant introuvable";	

	// SOAP Station
	public static final String ERR_STATION_INTROUVABLE 	= "Station introuvable";
	
	// SOAP Prix Carburant

	public static final String ERR_PRIX_INTROUVABLE 	= "Prix carburant introuvable";
	
	
	// SOAP Utilisateur

	public static final String MSG_VALIDATION_UTILISATEUR= "Validation d'un utilisateur: ";	

	public static final String MSG_VALIDATION_ERRONEE	 = "Validation erronée, utilisateur: ";

	public static final String MSG_VALIDATION_SUCCESS	 = "Validation avec succès, utilisateur: ";
	
	
	// Tests
	public static final String MSG_INVOCATION_TEST = "Invocation d'un test "
												 + "de services à partir "
												 + "d'un client....";
	
	public static final String MSG_TestLog		   = "Testing";
	
	// Urls
	public static final String LOCAL_HOST  = "http://localhost:8081";
	
	public static final String PATH_CARBURANTS = "gsc/carburants";
	public static final String PATH_STATIONS   = "gsc/stations";
	public static final String PATH_PRIX       = "gsc/prix";
	public static final String PATH_AUTH 	   = "gsc/authentification";

	

	
	
	/******************
	 * WS rest
	 ******************/
	/**
	 * Il faut concaténer le chemin au message.
	 */
	public static String MSG_CONFIGURATION_WEBAPP = "Configuration de l'application webapp, chemin utilisé : ";

	public static String MSG_DEMMARAGE_REST 	  = "Démarrage de l'application rest...";
	
	public static String MSG_ACCESS_REFUSE		  = "Vous n'avez pas de permission pour accéder à la ressource demandée.";

	public static String MSG_ACCESS_IMPOSSIBLE	  = "L'accès à la ressource demandée est impossible pour le moment.";

	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String MSG_VERIF_PERSMISSIONS	  = "Vérification en cours des permission de l'utilisateur ";

	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String MSG_CONNEXION_OK	  	  = "Connexion OK: ";
	
	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String ERR_CONNEXION_KO	  	  = "Connexion impossible avec l'utilisateur: ";
	
	public static String ERR_MDPASSE_MODIFICATION = "Mot de passe non modificable via cette méthode.";
	
	public static String ERR_AUTH_KO			  = "L'utilisateur ou le mot de passe saisi est incorrecte";
	
	
	/*******************
	 * Message en général
	 ******************/
	public static String MSG_INIT_TOMCAT = "Initialisation du serveur Tomcat...";
	
	
	/******************
	 * Symboles
	 ******************/
	/**
	 * Le symbole slash "/" utilisé souvent dans plusieurs cancaténations.
	 */
	public static String SMB_SLASH = "/";

	
	
	
}
