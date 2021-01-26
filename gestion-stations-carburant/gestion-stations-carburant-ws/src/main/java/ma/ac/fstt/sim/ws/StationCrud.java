package ma.ac.fstt.sim.ws;

import java.util.List;

import javax.jws.*;

import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.ws.dto.StationDto;

@WebService
public interface StationCrud {
	
	
	
	@WebMethod
	public StationDto findStationById(int id);
	
	@WebMethod
	boolean createStation(StationDto station);
	
	@WebMethod
	boolean updateStation(StationDto station);
	
	@WebMethod
	boolean deleteStation(int id);
	
	@WebMethod
	List<StationDto> getAllStations();
	
	
}
