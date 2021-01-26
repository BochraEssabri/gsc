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
@Table(name="station")
public class Station implements Serializable{
	
	/**
	 * La déclaration de cette serialVersionUID est exigée par l'éditeur
	 * puisque la classe est serialisée.
	 */
	private static final long serialVersionUID = 5763795231339635703L;

	// la clé primaire (primary key dans la table)
	@Id
	@Column(name="id_station")
	private int idStation;
	
	@Column(name="nom_station")
	private String nomStation;

	@Column(name="ville")
	private String ville;

	@Column(name="adresse")
	private String adresse;
	
	// Pour définir une relation [station] n <====> 1 [HistoCarb]
	// ref:https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/ 
	// EAGER pour charger la liste quand on récupère une station.
	@OneToMany( cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
        	orphanRemoval = true,
        	mappedBy = "station")
	private List<HistoCarb> histoCarbs;

	
	public Station() {

	}

	/**
	 * 
		getter pour récupérer l'id de la station
	 * @return
	 */
	public int getIdStation() {
		return idStation;
	}
	
	/**
	 * setter pour saisir l'id de la station
	 * @param idStation
	 */
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<HistoCarb> getHistoCarbs() {
		return histoCarbs;
	}

	public void setHistoCarbs(List<HistoCarb> histoCarbs) {
		this.histoCarbs = histoCarbs;
	}
	
	

}
