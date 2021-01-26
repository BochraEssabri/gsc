package ma.ac.fstt.sim.ws;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.dao.StationDao;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.ws.config.ServicesSoapConfig;
import ma.ac.fstt.sim.ws.dto.StationDto;

/**
 * On hérite de la classe ServicesSoapConfig pour valider la clé d'authentification.
 * Cette classe, on l'a créée nous même.
 * L'implementation de l'interface est pour le but de respecter la synthaxe JAX WS indiquée
 * Dans sa documentation, ref: https://www.baeldung.com/jax-ws.
 * @author ESSABRI & SARJANE
 *
 */
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.StationCrud")
public class StationCrudImpl extends ServicesSoapConfig  implements StationCrud {
	
	private static final Logger logger = Log.getLogger(StationCrudImpl.class);
	
	// Propriété injectée par JAX-WS et utilisée pour récupérer les headers de la requête.
	// Ici, utilisée essentiellement pour récupérer la clé d'authentification.
	@Resource
    WebServiceContext webServiceContext;
	
	StationDao stationDao = new StationDao();
	
	
	@Override
	public StationDto findStationById(int id) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		logger.info(Messages.MSG_STATION_RCHRCH + id);
		
		StationDto stationDto = null;
		
		if(id <= 0) {
			logger.error(Messages.ERR_PARAM);
			return stationDto;
		}
		
		try {
			logger.info(Messages.MSG_INVOCATION_BD);
			
			Station station = stationDao.find(id);
			
			if(station != null)
			{
				stationDto = new StationDto(station);
				logger.info(Messages.MSG_STATION_TROUVE);
			}
			else {
				logger.info(Messages.ERR_STATION_INTROUVABLE);
			}
					
			return stationDto;		
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		
		return stationDto;
	}
	

	@Override
	public boolean createStation(StationDto station) {

		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
				
		if(station == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}

		logger.info(Messages.MSG_STATION_ENRGSTRMT + station.getNomStation());

		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = stationDao.create(station.convertir());
		
		logger.info(Messages.MSG_STATION_ENREGISTRE + station.getNomStation());
		

		if(verification) {
			logger.info(Messages.MSG_STATION_ENREGISTRE);
		}
		
		return verification;
	}


	@Override
	public boolean updateStation(StationDto station) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
		if(station == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}
		
		logger.info(Messages.MSG_STATION_MODIF + station.getIdStation());		

		
		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = stationDao.update(station.convertir());
		

		if(verification) {
			logger.info(Messages.MSG_STATION_MODIFIE);
		}
		
		
		return verification;
	}


	@Override
	public boolean deleteStation(int id) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
		if(id <= 0) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}
		

		logger.info(Messages.MSG_STATION_SUPPRSSN + id);		

		
		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = stationDao.delete(id);

		if(verification) {
			logger.info(Messages.MSG_STATION_SUPPRM);
		}
		return verification;
	}


	@Override
	public List<StationDto> getAllStations() {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		List<StationDto> stationDtos = new ArrayList<StationDto>();
		
		logger.info(Messages.MSG_STATION_LIST);		

		logger.info(Messages.MSG_INVOCATION_BD);
		
		List<Station> caburantList = stationDao.getAll();
		
		if(caburantList == null) {
			
			
			logger.info(Messages.MSG_STATION_LIST_VIDE);
			
			return stationDtos;
		}
		
		for(Station station : caburantList) {
			stationDtos.add(new StationDto(station));
		}
		
		
		return stationDtos;
	}

}
