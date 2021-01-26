package ma.ac.fstt.sim.ws.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;

@XmlRootElement(name="CarburantDto")
public class CarburantDto {
	
	private int idCarburant;
	private String nomCarburant;
	private String description;
	private List<PrixDto> prixDtoList;

	public CarburantDto() {
		prixDtoList = new ArrayList<PrixDto>();
	}
	
	/**
	 * Créer DTO à partir d'une entité
	 * @param carburant
	 */
	public CarburantDto(Carburant carburant) {
		idCarburant 			= carburant.getIdCarburant();
		nomCarburant			= carburant.getNomCarburant();
		description 			= carburant.getDescription();
		
		// Car SOAP n'accepte pas les setters des listes.
		// Donc on garde l'instance pour en servir.
		prixDtoList = new ArrayList<PrixDto>();
		
		if(carburant.getHistoCarbs() != null) {
			List<HistoCarb> histoCarns = carburant.getHistoCarbs();
			for( HistoCarb histoCarb : histoCarns) {
				PrixDto prix = new PrixDto(histoCarb);
				prix.setCarburant(null);
				prixDtoList.add(prix);
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
	public List<PrixDto> getPrixDtoList() {
		return prixDtoList;
	}

	public void setPrixDtoList(List<PrixDto> prixDtoList) {
		this.prixDtoList = prixDtoList;
	}
	
	
	
	/**
	 * Convertir de DTO à DAO
	 * @return Carburant : Entité
	 */
	public Carburant convertir() {
		Carburant carburant = new Carburant();
		carburant.setIdCarburant(idCarburant);
		carburant.setNomCarburant(nomCarburant);
		carburant.setDescription(description);
		if(prixDtoList != null) {
			List<HistoCarb> histoCarbs = new ArrayList<HistoCarb>();
			
			for(PrixDto prixDto : prixDtoList) {
				if(prixDto != null) {
					histoCarbs.add(prixDto.convertir());
				}
			}
			carburant.setHistoCarbs(histoCarbs);
		}
		
		return carburant;
	}

	
}
