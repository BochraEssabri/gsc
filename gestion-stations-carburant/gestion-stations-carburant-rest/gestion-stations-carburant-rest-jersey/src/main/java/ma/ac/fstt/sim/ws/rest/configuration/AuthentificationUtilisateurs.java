package ma.ac.fstt.sim.ws.rest.configuration;

import com.sun.xml.ws.developer.WSBindingProvider;

import ma.ac.fstt.sim.generated.ws.client.UtilisateurCrud;
import ma.ac.fstt.sim.generated.ws.client.UtilisateurCrudImplService;
import ma.ac.fstt.sim.generated.ws.client.UtilisateurDto;
import ma.ac.fstt.sim.ws.rest.beans.Utilisateur;

public class AuthentificationUtilisateurs {
	
	final private static AuthentificationUtilisateurs instance = new AuthentificationUtilisateurs();
	
	// Service Soap pour l'authentification des utilisateurs.
	private UtilisateurCrud authentificationSoapService;
	
	// On ajoute la clé d'authentification au service SOAP.
	AuthenificationSoap authenificationSoap = AuthenificationSoap.getInstance();

	private AuthentificationUtilisateurs() {
		UtilisateurCrudImplService serviceSoap = new UtilisateurCrudImplService();
		authentificationSoapService = serviceSoap.getUtilisateurCrudImplPort();
		
		// On ajoute les informations de connexion pour pouvoir communiquer avec SOAP.
		authenificationSoap.injecterAuthentification((WSBindingProvider) authentificationSoapService);
	}
	
	public Utilisateur authentifierUtilisateur(String nom, String motDePasse) {
		Utilisateur utilisateur = null;
		
		UtilisateurDto utilisateurDto = authentificationSoapService.validerUtilisateur(nom, motDePasse);
		
		
		//Cas d'authentification erronée		 
		if(utilisateurDto == null )
		{
			return null;
		}
		
		utilisateur = new Utilisateur(utilisateurDto);
		
		return utilisateur;
	}
	
	public static AuthentificationUtilisateurs getInstance() {
		return instance;
	}
	
}
