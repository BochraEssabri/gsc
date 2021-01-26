package ma.ac.fstt.sim.ws.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

import ma.ac.fstt.sim.jpa.entities.HistoCarb;

@XmlRootElement(name="PrixDto")
public class PrixDto implements Serializable{
	
	private int 		 idHistoCarb;
	private String  	 date;
	private double 		 prix;
    private StationDto   station;
    private CarburantDto carburant;

	
	public PrixDto() {

	}
	
	/**
	 * Créer DTO à partir d'une entité
	 * @param histoCarb
	 */
	public PrixDto(HistoCarb histoCarb) {
		idHistoCarb = histoCarb.getIdHistoCarb();
		date		= histoCarb.getDate();
		prix		= histoCarb.getPrix();
		
		// Pour eviter une boucle infinie (relations réciproques)
		if(histoCarb.getStation()!=null) {
			station		= new StationDto();
			station.setIdStation(histoCarb.getStation().getIdStation());
			station.setNomStation(histoCarb.getStation().getNomStation());
			station.setAdresse(histoCarb.getStation().getAdresse());
			station.setVille(histoCarb.getStation().getVille());
		}
		
		if( histoCarb.getCarburant()!=null ) {
			carburant   = new CarburantDto();
			carburant.setIdCarburant(histoCarb.getCarburant().getIdCarburant());
			carburant.setNomCarburant(histoCarb.getCarburant().getNomCarburant());
			carburant.setDescription(histoCarb.getCarburant().getDescription());
		}

	}

	
	public int getIdHistoCarb() {
		return idHistoCarb;
	}


	public void setIdHistoCarb(int idHistoCarb) {
		this.idHistoCarb = idHistoCarb;
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


	public StationDto getStation() {
		return station;
	}


	public void setStation(StationDto station) {
		this.station = station;
	}


	public CarburantDto getCarburant() {
		return carburant;
	}


	public void setCarburant(CarburantDto carburant) {
		this.carburant = carburant;
	}

	/**
	 * Convertir de DTO à DAO
	 * @return HistoCarb : Entité
	 */
	public HistoCarb convertir() {
		HistoCarb histoCarb = new HistoCarb();
		histoCarb.setIdHistoCarb(idHistoCarb);
		histoCarb.setDate(date);
		histoCarb.setPrix(prix);
		
		if(carburant != null) {
			histoCarb.setCarburant(carburant.convertir());
		}
		
		if(station != null) {
			histoCarb.setStation(station.convertir());
		}
		return histoCarb;
	}
	
	
	
	
}
