package ma.ac.fstt.sim.ws;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.dao.CarburantDao;
import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.ws.config.ServicesSoapConfig;
import ma.ac.fstt.sim.ws.dto.CarburantDto;

/**
 * On hérite de la classe ServicesSoapConfig pour valider la clé d'authentification.
 * Cette classe, on l'a créée nous même.
 * L'implementation de l'interface est pour le but de respecter la synthaxe JAX WS indiquée
 * Dans sa documentation, ref: https://www.baeldung.com/jax-ws.
 * @author ESSABRI & SARJANE
 *
 */
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.CarburantCrud")
public class CarburantCrudImpl extends ServicesSoapConfig implements CarburantCrud {
	
	private static final Logger logger = Log.getLogger(CarburantCrudImpl.class);

	// Propriété injectée par JAX-WS et utilisée pour récupérer les headers de la requête.
	// Ici, utilisée essentiellement pour récupérer la clé d'authentification.
	@Resource
    WebServiceContext webServiceContext;
	
	CarburantDao carburantDao = new CarburantDao();

	
	@Override
	public CarburantDto findCarburantById(int id) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		logger.info(Messages.MSG_CARBURANT_RCHRCH + id);
		
		CarburantDto carburantDto = null;
		
		if(id <= 0) {
			logger.error(Messages.ERR_PARAM);
			return carburantDto;
		}
		
		try {
			logger.info(Messages.MSG_INVOCATION_BD);
			
			Carburant carburant = carburantDao.find(id);
			
			if(carburant != null)
			{
				carburantDto = new CarburantDto(carburant);
				logger.info(Messages.MSG_CARBURANT_TROUVE);
			}
			else {
				logger.info(Messages.ERR_CARBURANT_INTROUVABLE);
			}
					
			return carburantDto;		
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		
		return carburantDto;
	}
	

	@Override
	public boolean createCarburant(CarburantDto carburant) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
				
		if(carburant == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}

		logger.info(Messages.MSG_CARBURANT_ENRGSTRMT + carburant.getNomCarburant());

		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = carburantDao.create(carburant.convertir());
		
		if(verification) {
			logger.info(Messages.MSG_CARBURANT_ENREGISTRE + carburant.getNomCarburant());
		}
		
		return verification;
	}


	@Override
	public boolean updateCarburant(CarburantDto carburant) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
		if(carburant == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}
		
		logger.info(Messages.MSG_CARBURANT_MODIF + carburant.getIdCarburant());		

		
		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = carburantDao.update(carburant.convertir());
		
		if(verification) {
			logger.info(Messages.MSG_CARBURANT_MODIFIE);
		}
		
		return verification;
	}


	@Override
	public boolean deleteCarburant(int id) {
		
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
		

		logger.info(Messages.MSG_CARBURANT_SUPPRSSN + id);		

		
		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = carburantDao.delete(id);
		
		if(verification) {
			logger.info(Messages.MSG_CARBURANT_SUPPRM);
		}
		
		
		return verification;
	}


	@Override
	public List<CarburantDto> getAllCarburants() {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		List<CarburantDto> carburantDtos = new ArrayList<CarburantDto>();
		
		logger.info(Messages.MSG_CARBURANT_LIST);		

		logger.info(Messages.MSG_INVOCATION_BD);
		
		List<Carburant> caburantList = carburantDao.getAll();
		
		if(caburantList == null) {
			
			logger.info(Messages.MSG_CARBURANT_LIST_VIDE);
			
			return carburantDtos;
		}
		
		for(Carburant carburant : caburantList) {
			carburantDtos.add(new CarburantDto(carburant));
		}
		
		
		return carburantDtos;
	}


	

}
