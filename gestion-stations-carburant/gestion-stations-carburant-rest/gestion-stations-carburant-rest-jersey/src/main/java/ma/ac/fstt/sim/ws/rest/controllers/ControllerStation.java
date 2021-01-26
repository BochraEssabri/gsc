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

import ma.ac.fstt.sim.generated.ws.client.StationCrud;
import ma.ac.fstt.sim.generated.ws.client.StationCrudImplService;
import ma.ac.fstt.sim.generated.ws.client.StationDto;
import ma.ac.fstt.sim.ws.rest.beans.Station;
import ma.ac.fstt.sim.ws.rest.configuration.AuthenificationSoap;

@Path("/stations")
public class ControllerStation {
	
	StationCrud stationService;

	public ControllerStation() {
		StationCrudImplService stationCrudImplService = new StationCrudImplService();

		stationService = stationCrudImplService.getStationCrudImplPort();
		
		// On ajoute la clé d'authentification au service SOAP.
		AuthenificationSoap.getInstance().injecterAuthentification((WSBindingProvider) stationService);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	@RolesAllowed("UTILISATEUR")
	public List<Station> getAllStations() {

		List<StationDto> stations  = stationService.getAllStations();
		
		List<Station> listStations = new ArrayList<Station>();
		
		for(StationDto stationDto : stations) {
			listStations.add(new Station(stationDto));
		}
		
		return listStations;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	@RolesAllowed("UTILISATEUR")
	public Station findStation(@QueryParam("id") Integer id) {

		StationDto stationDto = stationService.findStationById(id);

		return new Station(stationDto);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	@RolesAllowed("UTILISATEUR")
	public boolean createStation(Station station) {
		
		StationDto stationDto = station.convertir();
		
		boolean verification = stationService.createStation(stationDto);

		return verification;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	@RolesAllowed("UTILISATEUR")
	public boolean updateStation(Station station) {
		
		StationDto stationDto = station.convertir();
		
		boolean verification = stationService.updateStation(stationDto);
		
		return verification;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@RolesAllowed("UTILISATEUR")
	public boolean deleteStation(@QueryParam("id") Integer id) {

		boolean verification = stationService.deleteStation(id);

		return verification;
	}


}
