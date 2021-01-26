package ma.ac.fstt.sim.ws.rest.beans;



import ma.ac.fstt.sim.generated.ws.client.CarburantDto;
import ma.ac.fstt.sim.generated.ws.client.PrixDto;
import ma.ac.fstt.sim.generated.ws.client.StationDto;

public class PrixCarburant {
	
	
	private int 		idHistoCarb;
	private String 		date;
	private double 		prix;
	private Carburant 	carburant;
	private Station 	station;
	

	public PrixCarburant() {
		
	}
	

	/**
	 * SOAP
	 */
	public PrixCarburant(PrixDto prixDto) {
		
		idHistoCarb = prixDto.getIdHistoCarb();
		
		date		= prixDto.getDate();
		
		prix		= prixDto.getPrix();
		
		CarburantDto carburantDto = prixDto.getCarburant();
		
		if( carburantDto != null ) {
			carburant = new Carburant(carburantDto);
		}
		
		
		StationDto stationDto = prixDto.getStation();
		
		if(stationDto != null) {
			station = new Station(stationDto);
		}
	}
	
	public PrixDto convertir() {
		PrixDto prixDto = new PrixDto();
		prixDto.setDate(date);
		prixDto.setPrix(prix);
		prixDto.setCarburant(carburant.convertir());
		prixDto.setStation(station.convertir());
		return prixDto;
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


	public Carburant getCarburant() {
		return carburant;
	}


	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}


	public Station getStation() {
		return station;
	}


	public void setStation(Station station) {
		this.station = station;
	}
	


	
	
}
