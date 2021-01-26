package ma.ac.fstt.sim.commun.ressoureces;

public class Messages {
	
	/*****************
	 * Base de donn�es
	 ******************/
	public static String MSG_TESANSACTION_COMMENCEE 	= "Execution d'une requ�te en base de donn�es...";

	public static String MSG_TESANSACTION_EXECUTEE 		= "Requ�te en base de donn�es execut�e !";

	public static String MSG_TESANSACTION_OK 			= "Execution d'une requ�te en base de donn�es OK !";

	public static String ERR_TESANSACTION_KO 			= "Une erreur lors de l'execution d'une requ�te en base de donn�es.";
	
	/**
	 * Il faut ajouter l'element concern� apr�s les deux points.
	 */
	public static String ERR_OPERATION_INDISPONIBLE 	= "Cette op�ration est indisponible pour : ";

	
	/******************
	 * WS SOAP
	 ******************/
	
	//// Messages ////
	

	public static final String MSG_DEMARRAGE_APPLI 		= "Demarrage de l'application...";
	
	public static final String MSG_INVOCATION_BD	    = "Invocation de la BD en cours...";
	
	public static final String MSG_SERVICE_OK 			= "Les services sont disponibles!";
	
	public static final String MSG_PUBLICATION_SERVICES = "Publication de services SOAP...";
	
	public static final String MSG_PUBLICATION 			= "Publication de ";	
	
	public static final String ERR_ACCES_NON_AUTORISE	= "Tentative d'acc�s non autoris�!!";
	
	// SOAP Carburant
	
	public static final String MSG_CARBURANT_RCHRCH		= "Recherche d'un carburant: ";	
	
	public static final String MSG_CARBURANT_ENRGSTRMT	= "Enregistrement d'un carburant: ";	
	
	public static final String MSG_CARBURANT_SUPPRSSN	= "Supression d'un carburant: ";	
	
	public static final String MSG_CARBURANT_MODIF		= "Modification d'un carburant: ";	
	
	public static final String MSG_CARBURANT_LIST		= "R�cupration de la liste des carburants... ";	
	
	public static final String MSG_CARBURANT_LIST_VIDE	= "Aucun Carburant n'a �t� trouv� ... ";	
	
	public static final String MSG_CARBURANT_TROUVE		= "Carburant trouv�: ";
	
	public static final String MSG_CARBURANT_ENREGISTRE	= "Carburant enregistr� : ";	
	
	public static final String MSG_CARBURANT_SUPPRM		= "Carburant supprim� : ";	
	
	public static final String MSG_CARBURANT_MODIFIE	= "Carburant modifi� : ";	
	
	
	// SOAP Station
	public static final String MSG_STATION_MODIF 		= "Modification d'une station: ";

	public static final String MSG_STATION_RCHRCH 		= "Recherche d'une station: ";	

	public static final String MSG_STATION_TROUVE 		= "Station trouv�e: ";	

	public static final String MSG_STATION_ENRGSTRMT 	= "Enregistrement d'une station: ";

	public static final String MSG_STATION_ENREGISTRE 	= "Station enregistr�e : ";

	public static final String MSG_STATION_SUPPRSSN 	= "Supression d'une station: ";

	public static final String MSG_STATION_LIST 		= "R�cupration de la liste des stations... ";	

	public static final String MSG_STATION_LIST_VIDE 	= "Aucune Station n'a �t� trouv�e ... ";	
	
	public static final String MSG_STATION_SUPPRM		= "Station supprim�e : ";	
	
	public static final String MSG_STATION_MODIFIE		= "Station modifi�e : ";	
	
	
	// SOAP Prix Carburant
	public static final String MSG_PRIX_RCHRCH 			= "Recherche de prix de carburant: ";	

	public static final String MSG_PRIX_TROUVE 			= "Prix trouv�: ";

	public static final String MSG_PRIX_ENRGSTRMT 		= "Enregistrement d'un de carburant: ";

	public static final String MSG_PRIX_ENREGISTRE 		= "Prix carburant enregistr� : ";

	public static final String MSG_PRIX_MODIF 			= "Modification du prix: ";

	public static final String MSG_PRIX_MODIFIE 		= "Prix carburant modifi� : ";

	public static final String MSG_PRIX_SUPPRSSN 		= "Supression du prix d'un carburant: ";

	public static final String MSG_PRIX_SUPPRM 			= "Prix carburant supprim� : ";	

	public static final String MSG_PRIX_LIST 			= "R�cupration de la liste des prix de carburants... ";	

	public static final String MSG_PRIX_LIST_VIDE 		= "Aucun prix carburant n'a �t� trouv� ... ";

	//// Erreurs ////
	
	public static final String ERR_PARAM				= "Erreur: Param�tre Incorrecte";	
	
	// SOAP Carburant

	public static final String ERR_CARBURANT_INTROUVABLE= "Carburant introuvable";	

	// SOAP Station
	public static final String ERR_STATION_INTROUVABLE 	= "Station introuvable";
	
	// SOAP Prix Carburant

	public static final String ERR_PRIX_INTROUVABLE 	= "Prix carburant introuvable";
	
	
	// SOAP Utilisateur

	public static final String MSG_VALIDATION_UTILISATEUR= "Validation d'un utilisateur: ";	

	public static final String MSG_VALIDATION_ERRONEE	 = "Validation erron�e, utilisateur: ";

	public static final String MSG_VALIDATION_SUCCESS	 = "Validation avec succ�s, utilisateur: ";
	
	
	// Tests
	public static final String MSG_INVOCATION_TEST = "Invocation d'un test "
												 + "de services � partir "
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
	 * Il faut concat�ner le chemin au message.
	 */
	public static String MSG_CONFIGURATION_WEBAPP = "Configuration de l'application webapp, chemin utilis� : ";

	public static String MSG_DEMMARAGE_REST 	  = "D�marrage de l'application rest...";
	
	public static String MSG_ACCESS_REFUSE		  = "Vous n'avez pas de permission pour acc�der � la ressource demand�e.";

	public static String MSG_ACCESS_IMPOSSIBLE	  = "L'acc�s � la ressource demand�e est impossible pour le moment.";

	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String MSG_VERIF_PERSMISSIONS	  = "V�rification en cours des permission de l'utilisateur ";

	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String MSG_CONNEXION_OK	  	  = "Connexion OK: ";
	
	/**
	 * A completer par le nom de l'utilisateur
	 */
	public static String ERR_CONNEXION_KO	  	  = "Connexion impossible avec l'utilisateur: ";
	
	public static String ERR_MDPASSE_MODIFICATION = "Mot de passe non modificable via cette m�thode.";
	
	public static String ERR_AUTH_KO			  = "L'utilisateur ou le mot de passe saisi est incorrecte";
	
	
	/*******************
	 * Message en g�n�ral
	 ******************/
	public static String MSG_INIT_TOMCAT = "Initialisation du serveur Tomcat...";
	
	
	/******************
	 * Symboles
	 ******************/
	/**
	 * Le symbole slash "/" utilis� souvent dans plusieurs cancat�nations.
	 */
	public static String SMB_SLASH = "/";

	
	
	
}
