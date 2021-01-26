package ma.ac.fstt.sim.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * l'implementation de serialisable permet à JPA de traiter cette classe
 * dans les communications avec la BD en la formant partie par partie ( en série )
 * Réf: https://www.javahelps.com/2015/08/jpa-hello-world-using-hibernate.html
 */
@Entity
@Table(name="carburant")
public class Carburant implements Serializable{
	
	/**
	 * La déclaration de cette serialVersionUID est exigée par l'éditeur
	 * puisque la classe est serialisée.
	 */
	private static final long serialVersionUID = 6753525546956647167L;

	// la clé primaire (primary key dans la table)
	@Id
	@Column(name="id_carburant")
	private int idCarburant;
	
	// une colonne dans la table
	@Column(name="nom_carburant")
	private String nomCarburant;
	

	@Column(name="description")
	private String description;
	
	// Pour définir une relation [carburant] n <====> 1 [HistoCarb]
	// ref:https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/ 
	// EAGER pour charger la liste quand on récupère une station.
	@OneToMany( cascade = CascadeType.ALL,
				fetch = FetchType.EAGER,
	        	orphanRemoval = true,
	        	mappedBy = "carburant")
	//@JsonIgnoreProperties(value = "carburant", allowSetters = true)
	private List<HistoCarb> histoCarbs;
	
	public Carburant() {

	}
	
	

	/**
	 * getter pour récupérer l'id du carburant
	 * @return
	 */
	public int getIdCarburant() {
		return idCarburant;
	}
	
	/**
	 * setter pour modifier l'id du carburant
	 * @param idCarburant
	 */
	public void setIdCarburant(int idCarburant) {
		this.idCarburant = idCarburant;
	}

	/**
	 * getter pour récupérer le nom du carburant.
	 * @return
	 */
	public String getNomCarburant() {
		return nomCarburant;
	}

	/**
	 * setter pour modifier le nom du carburant
	 * @param nomCarburant
	 */
	public void setNomCarburant(String nomCarburant) {
		this.nomCarburant = nomCarburant;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}


	public List<HistoCarb> getHistoCarbs() {
		return histoCarbs;
	}


	public void setHistoCarbs(List<HistoCarb> histoCarbs) {
		this.histoCarbs = histoCarbs;
	}
	
	
	
	
	
	
	
}
