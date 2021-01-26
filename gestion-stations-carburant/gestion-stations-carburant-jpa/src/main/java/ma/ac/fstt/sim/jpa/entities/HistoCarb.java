package ma.ac.fstt.sim.jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * l'implementation de serialisable permet � JPA de traiter cette classe
 * dans les communications avec la BD en la formant partie par partie ( en s�rie )
 * R�f: https://www.javahelps.com/2015/08/jpa-hello-world-using-hibernate.html
 */
/**
 * @IdClass sert de la classe CleComposee pour former une cl� primaire compos�e.
 * La cl� est form�e de deux colonnes.
 * https://stackoverflow.com/questions/41143913/sql-jpa-multiple-columns-as-primary-key
 */
@Entity
@Table(name="histo_carb")
public class HistoCarb implements Serializable{
	
	/**
	 * La d�claration de cette serialVersionUID est exig�e par l'�diteur
	 * puisque la classe est serialis�e.
	 */
	private static final long serialVersionUID = 7236788067294553884L;
	
	/**
	 * Pour assuer le respet de la forme yyyy-MM-dd dans la date.
	 */
	public static final String DATE_REG_EX = "((?:19|20)\\d\\d)-(0?[1-9]|1[012])-([12][0-9]|3[01]|0?[1-9])";
	
	/**
	 *
	 * il faut rajouter @Id dessus.
	 */
	
	@Column(name="id_histo_carb")
	@Id
	private int idHistoCarb;
	
	@Column(name="date")
	private String date;
	

	// une colonne dans la table
	@Column(name="prix")
	private double prix;

	/**
	 * Cel� repr�sente la relation inverse [HistoCarb] n <====> 1 [station]
	 * LAZY veut dire que JPA ne va pas charger toutes les donn�es en relation avec station
	 * avant qu'on aura besoin de ces donn�es.
	 * Le contraire c'est EAGER, cel� veut dire qu'au moment de chargement de donn�es,
	 * On va r�cup�r� toutes les lignes des classes qui sont en relation.
	 * 
	 * JoinColum contient la colonne qui cr�e la relation.
	 */
    @ManyToOne(fetch = FetchType.LAZY /*EAGER*/)
    @JoinColumn(name="id_station")
    private Station station;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_carburant")
    private Carburant carburant;
    
	
	
	
	public HistoCarb() {

	}

	
	public int getIdHistoCarb() {
		return idHistoCarb;
	}


	public void setIdHistoCarb(int idHistoCarb) {
		this.idHistoCarb = idHistoCarb;
	}

	

	public Station getStation() {
		return station;
	}


	public void setStation(Station station) {
		this.station = station;
	}


	public Carburant getCarburant() {
		return carburant;
	}


	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	
	
	
	
}
