package ma.ac.fstt.sim.ws.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.xml.ws.developer.WSBindingProvider;

import ma.ac.fstt.sim.generated.ws.client.PrixCrud;
import ma.ac.fstt.sim.generated.ws.client.PrixCrudImplService;
import ma.ac.fstt.sim.generated.ws.client.PrixDto;
import ma.ac.fstt.sim.generated.ws.client.StationDto;
import ma.ac.fstt.sim.ws.rest.beans.PrixCarburant;
import ma.ac.fstt.sim.ws.rest.configuration.AuthenificationSoap;

@Path("/prixCarburants")
public class ControllerPrixCarburant {
	private PrixCrud prixCarburantService;
	
	// On ajoute la clé d'authentification au service SOAP.
	private AuthenificationSoap authenificationSoap = AuthenificationSoap.getInstance();

	public ControllerPrixCarburant() {
		
		PrixCrudImplService prixCarburantCrudImplService = new PrixCrudImplService();
		
		prixCarburantService = prixCarburantCrudImplService.getPrixCrudImplPort();
		
		authenificationSoap.injecterAuthentification((WSBindingProvider) prixCarburantService);
		
	}
	
	
	/**
	 * Trouve le prix des deux carburants Gazole et Essence pour une station et une date données
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/findPrixStationDate")
	@RolesAllowed("UTILISATEUR")
	public List<PrixCarburant> findPrixStationDate(@QueryParam("idStation") Integer idStation, @QueryParam("date") String date) {
		
		StationDto stationDto = new StationDto();
		stationDto.setIdStation(idStation);
		
		List<PrixDto> listPrixDto = prixCarburantService.findPrixStationDate(stationDto, date);
		
		List<PrixCarburant> listPrixCarburants = new ArrayList<PrixCarburant>();
		
		for(PrixDto prixDto : listPrixDto) {
			listPrixCarburants.add(new PrixCarburant(prixDto));
		}
		
		return listPrixCarburants;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	@RolesAllowed("UTILISATEUR")
	public List<PrixCarburant> getAllPrixCarburants() {

		List<PrixDto> prixCarburants = prixCarburantService.getAllPrixs();
		
		List<PrixCarburant> listPrixCarburants = new ArrayList<PrixCarburant>();
		
		for(PrixDto prixDto : prixCarburants) {
			listPrixCarburants.add(new PrixCarburant(prixDto));
		}
		
		return listPrixCarburants;
	}	


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	@RolesAllowed("UTILISATEUR")
	public PrixCarburant findPrixCarburant(@QueryParam("id") Integer id) {

		PrixDto prixDto = prixCarburantService.findPrixById(id);

		return new PrixCarburant(prixDto);
	}

	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	@RolesAllowed("UTILISATEUR")
	public boolean createPrixCarburant(PrixCarburant prixCarburant) {
		
		PrixDto prixDto = prixCarburant.convertir();
		
		boolean verification = prixCarburantService.createPrix(prixDto);

		return verification;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	@RolesAllowed("UTILISATEUR")
	public boolean updatePrixCarburant(PrixCarburant prixCarburant) {
		
		PrixDto prixDto = prixCarburant.convertir();
		
		boolean verification = prixCarburantService.updatePrix(prixDto);
		
		return verification;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@RolesAllowed("UTILISATEUR")
	public boolean deletePrixCarburant(@QueryParam("id") Integer id) {

		boolean verification = prixCarburantService.deletePrix(id);

		return verification;
	}


}
