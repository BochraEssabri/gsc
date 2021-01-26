package ma.ac.fstt.sim.ws;

import java.util.List;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.ws.dto.CarburantDto;


@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface CarburantCrud {
	
	@WebMethod
	public CarburantDto findCarburantById(int id);
	
	//@WebMethod
	//public CarburantDto findCarburantRequestById(int id);
	
	
	@WebMethod
	boolean createCarburant(CarburantDto carburant);

	@WebMethod
	boolean updateCarburant(CarburantDto carburant);
	
	@WebMethod
	boolean deleteCarburant(int id);
	
	@WebMethod
	List<CarburantDto> getAllCarburants();
	
	
}
