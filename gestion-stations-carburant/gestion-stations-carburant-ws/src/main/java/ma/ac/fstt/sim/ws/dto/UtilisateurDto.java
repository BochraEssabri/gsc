package ma.ac.fstt.sim.ws.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;

@XmlRootElement(name="UtilisateurDto")
public class UtilisateurDto {
	
	/**
	 * Au lieu de renvoyer le mot de passe réel aux clients, on envoie cette valeur
	 * qui indique que le mot de passe a été validé avant de renvoyer 
	 * un objet de type UtilisateurDto.
	 * Notre client sait traiter ça comme étant déjà vérifié et validé 
	 * par notre server SOAP.
	 */
	final private String MDPASSE_INTERMEDIAIRE = "VALIDATION_SOAP";
	
	private int    idUtilisateur;
	private String nomUtilisateur;
	private String motDePasse;
	private String role;	

	public UtilisateurDto() {
	}
	
	/**
	 * Créer DTO à partir d'une entité
	 * @param station
	 */
	public UtilisateurDto(Utilisateur utilisateur) {
		idUtilisateur 	= utilisateur.getIdUtilisateur();
		nomUtilisateur  = utilisateur.getNomUtilisateur();
		motDePasse		= MDPASSE_INTERMEDIAIRE;
		role			= utilisateur.getRole();

	}

	
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Convertir de DTO à DAO
	 * Le mot de passe n'est pas intégré pour raison de sécurité.
	 * Par contre, on envoie la valeur de MDPASSE_INTERMEDIAIRE
	 * pour indiquer qu'on a bien validé le mot de passe utilisateur.
	 * @return Station : Entité
	 */
	public Utilisateur convertir() {
		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setIdUtilisateur(idUtilisateur);
		utilisateur.setNomUtilisateur(nomUtilisateur);
		utilisateur.setRole(role);
		
		/*
		 Au lieu de renvoyer le mot de passe réel aux clients, on envoie cette valeur
		 qui indique que le mot de passe a été validé avant de renvoyer 
		 un objet de type UtilisateurDto.
		 Notre client sait traiter ça comme étant déjà vérifié et validé 
		 par notre server SOAP.
		 */
		utilisateur.setMotDePasse(MDPASSE_INTERMEDIAIRE);
		
		return utilisateur;
	}
	
	
}
