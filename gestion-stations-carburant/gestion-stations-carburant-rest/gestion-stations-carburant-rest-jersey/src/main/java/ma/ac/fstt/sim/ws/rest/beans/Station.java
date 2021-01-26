package ma.ac.fstt.sim.ws.rest.beans;

import java.util.ArrayList;
import java.util.List;
import ma.ac.fstt.sim.generated.ws.client.PrixDto;
import ma.ac.fstt.sim.generated.ws.client.StationDto;

public class Station {
	
	private int 				idStation;
	private String 				nomStation;
	private String 				ville;
	private String 				adresse;	
	private List<PrixCarburant> prixCarburantList;

	public Station() {
		
	}
	

	/**
	 * SOAP
	 */
	public Station(StationDto stationDto) {
		idStation			= stationDto.getIdStation();
		nomStation			= stationDto.getNomStation();
		ville				= stationDto.getVille();
		adresse				= stationDto.getAdresse();
		prixCarburantList   = new ArrayList<PrixCarburant>();
		
		List<PrixDto> prixDtoList = stationDto.getPrixDtoList();
		
		if(prixDtoList != null) {
			for(PrixDto prixDto : prixDtoList ) {
				prixCarburantList.add(new PrixCarburant(prixDto));
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

	public List<PrixCarburant> getPrixCarburantList() {
		return prixCarburantList;
	}

	public void setPrixCarburantList(List<PrixCarburant> prixCarburantList) {
		this.prixCarburantList = prixCarburantList;
	}
	
	public StationDto convertir() {
			
			StationDto stationDto = new StationDto();
			
			stationDto.setIdStation(idStation);
			stationDto.setNomStation(nomStation);
			stationDto.setAdresse(adresse);
			stationDto.setVille(ville);
			if(prixCarburantList != null) {
				List<PrixDto> prixDtoList = stationDto.getPrixDtoList();
				
				for(PrixCarburant prixCarburant : prixCarburantList) {
					prixDtoList.add(prixCarburant.convertir());
				}
			}
			return stationDto;
		}
		
	
	
	
	
	
	
}
