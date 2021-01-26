package ma.ac.fstt.sim.ws;

import java.util.List;

import javax.jws.*;

import ma.ac.fstt.sim.jpa.dao.StationDao;
import ma.ac.fstt.sim.ws.dto.PrixDto;
import ma.ac.fstt.sim.ws.dto.StationDto;

@WebService
public interface PrixCrud {
	
	
	
	@WebMethod
	public PrixDto findPrixById(int id);
	
	@WebMethod
	boolean createPrix(PrixDto prixDto);

	@WebMethod
	boolean updatePrix(PrixDto prixDto);
	
	@WebMethod
	boolean deletePrix(int id);
	
	@WebMethod
	List<PrixDto> getAllPrixs();
	
	@WebMethod
	List<PrixDto> findPrixStationDate(StationDto stationDto, String date);
	
	
	
}
