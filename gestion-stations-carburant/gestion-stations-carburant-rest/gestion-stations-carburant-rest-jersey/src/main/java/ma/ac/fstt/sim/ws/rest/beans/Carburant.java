package ma.ac.fstt.sim.ws.rest.beans;

import java.util.ArrayList;
import java.util.List;
import ma.ac.fstt.sim.generated.ws.client.CarburantDto;
import ma.ac.fstt.sim.generated.ws.client.PrixDto;

public class Carburant {
	
	private int 				idCarburant;
	
	private String 				nomCarburant;
	
	private String 				description;
	
	private List<PrixCarburant> prixCarburantList;

	

	public Carburant() {
		
	}
	
	/**
	 * SOAP
	 */
	public Carburant(CarburantDto carburantDto) {
		
		idCarburant  = carburantDto.getIdCarburant();
		
		nomCarburant = carburantDto.getNomCarburant();
		
		description  = carburantDto.getDescription();
		
		prixCarburantList = new ArrayList<PrixCarburant>();
		
		List<PrixDto> prixDtoList = carburantDto.getPrixDtoList();
		if(prixDtoList != null) {
			for(PrixDto prixDto : carburantDto.getPrixDtoList()) {
				prixCarburantList.add(new PrixCarburant(prixDto));
			}
		}
		
	}
	
	
	public int getIdCarburant() {
		return idCarburant;
	}




	public void setIdCarburant(int idCarburant) {
		this.idCarburant = idCarburant;
	}




	public String getNomCarburant() {
		return nomCarburant;
	}




	public void setNomCarburant(String nomCarburant) {
		this.nomCarburant = nomCarburant;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}


	public List<PrixCarburant> getPrixCarburantList() {
		return prixCarburantList;
	}

	public void setPrixCarburantList(List<PrixCarburant> prixCarburantList) {
		this.prixCarburantList = prixCarburantList;
	}

	public CarburantDto convertir() {
		
		CarburantDto carburantDto = new CarburantDto();
		
		carburantDto.setIdCarburant(idCarburant);
		carburantDto.setNomCarburant(nomCarburant);
		carburantDto.setDescription(description);
		
		if(prixCarburantList != null) {
			List<PrixDto> prixDtoList = carburantDto.getPrixDtoList();
			
			for(PrixCarburant prixCarburant : prixCarburantList) {
				prixDtoList.add(prixCarburant.convertir());
			}
			
		}
		
		return carburantDto;
	}
	
}
