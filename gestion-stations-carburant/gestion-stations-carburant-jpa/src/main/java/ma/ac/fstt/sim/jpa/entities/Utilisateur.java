package ma.ac.fstt.sim.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class Utilisateur {
	
	/**
	 * La déclaration de cette serialVersionUID est exigée par l'éditeur
	 * puisque la classe est serialisée.
	 */
	
	// la clé primaire (primary key dans la table)
	@Id
	@Column(name="id_utilisateur")
	private int idUtilisateur;
	
	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="mot_de_passe")
	private String motDePasse;
	
	@Column(name="role")
	private String role;

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
	
	
	

}
