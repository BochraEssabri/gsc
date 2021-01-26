package ma.ac.fstt.sim.ws.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;

@XmlRootElement(name="StationDto")
public class StationDto {
	
	private int idStation;
	private String nomStation;
	private String ville;
	private String adresse;	
	private List<PrixDto> prixDtoList;

	public StationDto() {
		prixDtoList = new ArrayList<PrixDto>();
	}
	
	/**
	 * Créer DTO à partir d'une entité
	 * @param station
	 */
	public StationDto(Station station) {
		idStation 	= station.getIdStation();
		nomStation  = station.getNomStation();
		ville 		= station.getVille();
		adresse		= station.getAdresse();
		
		// Car SOAP n'accepte pas les setters des listes.
		// Donc on garde l'instance pour en servir.
		prixDtoList = new ArrayList<PrixDto>();
		
		if(station.getHistoCarbs() != null) {
			
			for( HistoCarb histoCarb : station.getHistoCarbs()) {
				PrixDto prix = new PrixDto(histoCarb);
				prix.setStation(null);
				prixDtoList.add(prix);
			}
		}
	}

	public int getIdStation() {
		return idStation;
	}

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

	public List<PrixDto> getPrixDtoList() {
		return prixDtoList;
	}

	public void setPrixDtoList(List<PrixDto> histoCarbs) {
		this.prixDtoList = histoCarbs;
	}
	
	/**
	 * Convertir de DTO à DAO
	 * @return Station : Entité
	 */
	public Station convertir() {
		Station station = new Station();

		station.setIdStation(idStation);
		station.setNomStation(nomStation);
		station.setAdresse(adresse);
		station.setVille(ville);
		
		if(prixDtoList != null) {
			List<HistoCarb> histoCarbs = new ArrayList<HistoCarb>();
			
			for(PrixDto prixDto : prixDtoList) {
				histoCarbs.add(prixDto.convertir());
			}
			station.setHistoCarbs(histoCarbs);
		}
		return station;
	}
	
	
}
