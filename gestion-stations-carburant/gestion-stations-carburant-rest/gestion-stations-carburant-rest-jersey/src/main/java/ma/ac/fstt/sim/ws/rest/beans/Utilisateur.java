package ma.ac.fstt.sim.ws.rest.beans;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import ma.ac.fstt.sim.commun.exceptions.MotDePasseNonModifiableException;
import ma.ac.fstt.sim.generated.ws.client.UtilisateurDto;

/**
 * 
 * @author ESSABRI & SARJANE
 * Permet de gérer les utilisateurs connectés.
 * Principal est une intérface qui permet de gérer la sécurité en Java. 
 * Elle est utilisée par Jax RS.
 * Ref:https://simplapi.wordpress.com/2015/09/19/jersey-jax-rs-securitycontext-in-action/
 */
public class Utilisateur implements Principal {
	
	/**
	 * Au lieu de renvoyer le mot de passe réel par le service SOAP, 
	 * on a renvoyé la valeur de MDPASSE_SOAP qui indique que le mot de passe a été validé avant de renvoyer 
	 * un objet de type UtilisateurDto.
	 * Notre client sait traiter ça comme étant déjà vérifié et validé 
	 * par notre server SOAP.
	 */
	final private String MDPASSE_SOAP = "VALIDATION_SOAP";
	
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(UtilisateurDto utilisateur) {
		super();
		name 	 = utilisateur.getNomUtilisateur();
		password = MDPASSE_SOAP.equals(utilisateur.getMotDePasse())?
				   MDPASSE_SOAP : null;
		// Actuellement, coté base de données, on a considéré 
		// qu'un utilisateur admet un seul mot de passe pour 
		// simplifier le traitement. On peut faire mieux en cas de besoin.
		role	 = new ArrayList<String>();
		role.add(utilisateur.getRole());
	}

	/**
	 * Accessible via getters et setters
	 */
	private String name;
	
	/**
	 * Inaccessible.
	 * Une fois intialisé, il n'est plus modifiable.
	 * 
	 */
	private String password;
	
	private List<String> role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
	
	
	/**
	 * On autorise pas la modification du mot de passe
	 * Ceci est initialisé une seule fois par l'application
	 * au moment de la création d'un objet utilisateur.
	 * On l'a implementé pour pouvoir implémenter SecurityContext facilement.
	 * @param password
	 * @throws MotDePasseNonModificableException
	 */
	public void setPassword(String password) throws MotDePasseNonModifiableException {
		
		if( this.password != null ) {
			throw new MotDePasseNonModifiableException();
		}
		
		this.password = password;
	}
	
	
	/**
	 * Utile pour un cas d'utilisateur récupéré de la session
	 * C'est pour éviter d'invoquer la BD à chaque requête si 
	 * L'utilisateur existe déjà dans la session.
	 * @param name
	 * @param password
	 * @param listeRoles
	 * @return
	 */
	public boolean valider(String name, String password, List<String> listeRoles) {
		
		boolean roleValide = false;
		

        for (String role : listeRoles) {
        	if(role.contains(role)) {
        		roleValide = true;
        		break;
        	}
		}
		
		return    this.name != null
			   && this.name.equals(name)
			   && this.password != null
			   && ( this.password.equals(password))
			   && roleValide;
	}
	
	/**
	 * Authentification par SOAP.
	 * Validée dès que l'objet n'est pas null et le mot de pass = la valeur MDPASSE_SOAP renvoyée par SOAP.
	 * @return
	 */
	public boolean validerParSoap() {
		return MDPASSE_SOAP.equals(this.password);
	}
	
	/**
	 * Permet de vérifier que l'utilisateur possède l'un des rôles 
	 * necessaires pour accéder à une ressource.
	 * @return
	 */
	public boolean validerRoles(List<String> roles) {
		
		for(String role : roles) {
			if(this.role.contains(role)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public String recupererAuthentificationBase64() {
		return Base64.getEncoder()
	    .encodeToString((name + ":" + password).getBytes());
	}


	
	

	
}
