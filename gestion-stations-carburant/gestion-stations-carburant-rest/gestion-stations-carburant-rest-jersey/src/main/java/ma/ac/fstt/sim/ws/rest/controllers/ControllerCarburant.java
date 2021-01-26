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

import ma.ac.fstt.sim.generated.ws.client.CarburantCrud;
import ma.ac.fstt.sim.generated.ws.client.CarburantCrudImplService;
import ma.ac.fstt.sim.generated.ws.client.CarburantDto;
import ma.ac.fstt.sim.ws.rest.beans.Carburant;
import ma.ac.fstt.sim.ws.rest.configuration.AuthenificationSoap;

@Path("/carburants")
public class ControllerCarburant {
	
	CarburantCrud carburantService;

	public ControllerCarburant() {
		CarburantCrudImplService carburantCrudImplService = new CarburantCrudImplService();

		carburantService = carburantCrudImplService.getCarburantCrudImplPort();

		// On ajoute la clé d'authentification au service SOAP.
		AuthenificationSoap.getInstance().injecterAuthentification((WSBindingProvider) carburantService);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	@RolesAllowed("UTILISATEUR")
	public List<Carburant> getAllCarburants() {

		List<CarburantDto> carburants = carburantService.getAllCarburants();
		
		List<Carburant> listCarburants = new ArrayList<Carburant>();
		
		for(CarburantDto carburantDto : carburants) {
			listCarburants.add(new Carburant(carburantDto));
		}
		
		return listCarburants;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	@RolesAllowed("UTILISATEUR")
	public Carburant findCarburant(@QueryParam("id") Integer id) {

		CarburantDto carburantDto = carburantService.findCarburantById(id);

		return new Carburant(carburantDto);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	@RolesAllowed("UTILISATEUR")
	public boolean createCarburant(Carburant carburant) {
		
		CarburantDto carburantDto = carburant.convertir();
		
		boolean verification = carburantService.createCarburant(carburantDto);

		return verification;
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	@RolesAllowed("UTILISATEUR")
	public boolean updateCarburant(Carburant carburant) {
		
		CarburantDto carburantDto = carburant.convertir();
		
		boolean verification = carburantService.updateCarburant(carburantDto);
		
		return verification;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	@RolesAllowed("UTILISATEUR")
	public boolean deleteCarburant(@QueryParam("id") Integer id) {

		boolean verification = carburantService.deleteCarburant(id);

		return verification;
	}


}
