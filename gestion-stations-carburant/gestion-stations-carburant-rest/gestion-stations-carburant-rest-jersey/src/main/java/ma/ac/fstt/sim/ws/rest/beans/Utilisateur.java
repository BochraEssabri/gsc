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
 * Permet de g�rer les utilisateurs connect�s.
 * Principal est une int�rface qui permet de g�rer la s�curit� en Java. 
 * Elle est utilis�e par Jax RS.
 * Ref:https://simplapi.wordpress.com/2015/09/19/jersey-jax-rs-securitycontext-in-action/
 */
public class Utilisateur implements Principal {
	
	/**
	 * Au lieu de renvoyer le mot de passe r�el par le service SOAP, 
	 * on a renvoy� la valeur de MDPASSE_SOAP qui indique que le mot de passe a �t� valid� avant de renvoyer 
	 * un objet de type UtilisateurDto.
	 * Notre client sait traiter �a comme �tant d�j� v�rifi� et valid� 
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
		// Actuellement, cot� base de donn�es, on a consid�r� 
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
	 * Une fois intialis�, il n'est plus modifiable.
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
	 * Ceci est initialis� une seule fois par l'application
	 * au moment de la cr�ation d'un objet utilisateur.
	 * On l'a implement� pour pouvoir impl�menter SecurityContext facilement.
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
	 * Utile pour un cas d'utilisateur r�cup�r� de la session
	 * C'est pour �viter d'invoquer la BD � chaque requ�te si 
	 * L'utilisateur existe d�j� dans la session.
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
	 * Valid�e d�s que l'objet n'est pas null et le mot de pass = la valeur MDPASSE_SOAP renvoy�e par SOAP.
	 * @return
	 */
	public boolean validerParSoap() {
		return MDPASSE_SOAP.equals(this.password);
	}
	
	/**
	 * Permet de v�rifier que l'utilisateur poss�de l'un des r�les 
	 * necessaires pour acc�der � une ressource.
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
