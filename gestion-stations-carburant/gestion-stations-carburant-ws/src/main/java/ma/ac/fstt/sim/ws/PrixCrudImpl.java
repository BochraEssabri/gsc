package ma.ac.fstt.sim.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.commun.ressoureces.ProjectConfiguration;
import ma.ac.fstt.sim.jpa.dao.HistoCarbDao;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.ws.config.ServicesSoapConfig;
import ma.ac.fstt.sim.ws.dto.PrixDto;
import ma.ac.fstt.sim.ws.dto.StationDto;

/**
 * On hérite de la classe ServicesSoapConfig pour valider la clé d'authentification.
 * Cette classe, on l'a créée nous même.
 * L'implementation de l'interface est pour le but de respecter la synthaxe JAX WS indiquée
 * Dans sa documentation, ref: https://www.baeldung.com/jax-ws.
 * @author ESSABRI & SARJANE
 *
 */
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.PrixCrud")
public class PrixCrudImpl extends ServicesSoapConfig implements PrixCrud{
	
	private static final Logger logger = Log.getLogger(PrixCrudImpl.class);
	
	HistoCarbDao histoCarbDao = new HistoCarbDao();
	
	// Propriété injectée par JAX-WS et utilisée pour récupérer les headers de la requête.
	// Ici, utilisée essentiellement pour récupérer la clé d'authentification.
	@Resource
    WebServiceContext webServiceContext;
	

	@Override
	public PrixDto findPrixById(int id) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		logger.info(Messages.MSG_PRIX_RCHRCH + id);
		
		PrixDto prixDto = null;
		
		if(id <= 0) {
			logger.error(Messages.ERR_PARAM);
			return prixDto;	}
		
		try {
			logger.info(Messages.MSG_INVOCATION_BD);
			
			HistoCarb histoCarb = histoCarbDao.find(id);
			
			if(histoCarb != null)
			{
				prixDto = new PrixDto(histoCarb);
				logger.info(Messages.MSG_PRIX_TROUVE);
			}
			else {
				logger.info(Messages.ERR_PRIX_INTROUVABLE);
			}
					
			return prixDto;		
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		
		return prixDto;
	}
	

	@Override
	public boolean createPrix(PrixDto histoCarb) {
		
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}
		
		boolean verification = false;
		
		if(histoCarb == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}
		
		if(histoCarb.getCarburant() != null && histoCarb.getStation() != null && histoCarb.getDate() != null) {
			final String IDENTIFIANT_LOG  = "id"
										  + histoCarb.getIdHistoCarb()
										  + " station " 
										  + histoCarb.getCarburant().getIdCarburant()
										  + " carburant "
										  + histoCarb.getStation().getIdStation()
										  + " date "
										  + histoCarb.getDate();
			
			logger.info(Messages.MSG_PRIX_ENRGSTRMT + IDENTIFIANT_LOG);

			logger.info(Messages.MSG_INVOCATION_BD);
			
			verification = histoCarbDao.create(histoCarb.convertir());
			
			if(verification) {
				logger.info(Messages.MSG_PRIX_ENREGISTRE + IDENTIFIANT_LOG);
			}
		}
		
		
		return verification;
	}


	@Override
	public boolean updatePrix(PrixDto histoCarb) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return false;
		}	
		
		
		boolean verification = false;


		if(histoCarb == null) {
			logger.error(Messages.ERR_PARAM);
			return verification;
		}
		
		if(histoCarb.getCarburant() != null && histoCarb.getStation() != null && histoCarb.getDate() != null) {
			
			final String IDENTIFIANT_LOG  = "id"
										  + histoCarb.getIdHistoCarb()
										  + " station " 
										  + histoCarb.getCarburant().getIdCarburant()
										  + " carburant "
										  + histoCarb.getStation().getIdStation()
										  + " date "
										  + histoCarb.getDate();
			
			
		
			logger.info(Messages.MSG_PRIX_MODIF + IDENTIFIANT_LOG);		
	
			
			logger.info(Messages.MSG_INVOCATION_BD);
			
			verification = histoCarbDao.update(histoCarb.convertir());
			
			if(verification) {
				logger.info(Messages.MSG_PRIX_MODIFIE);
			}
		}
		
		return verification;
	}


	@Override
	public boolean deletePrix(int id) {
		
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
		

		logger.info(Messages.MSG_PRIX_SUPPRSSN + id);		

		
		logger.info(Messages.MSG_INVOCATION_BD);
		
		verification = histoCarbDao.delete(id);
		
		if(verification) {
			logger.info(Messages.MSG_PRIX_SUPPRM);
		}
		
		
		return verification;
	}


	@Override
	public List<PrixDto> getAllPrixs() {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		List<PrixDto> listPrixDtos = new ArrayList<PrixDto>();
		
		logger.info(Messages.MSG_PRIX_LIST);		

		logger.info(Messages.MSG_INVOCATION_BD);
		
		List<HistoCarb> caburantList = histoCarbDao.getAll();
		
		if(caburantList == null) {
			
			logger.info(Messages.MSG_PRIX_LIST_VIDE);
			
			return listPrixDtos;
		}
		
		for(HistoCarb histoCarb : caburantList) {
			listPrixDtos.add(new PrixDto(histoCarb));
		}
		
		
		return listPrixDtos;
	}


	@Override
	public List<PrixDto> findPrixStationDate(StationDto stationDto, String date) {
		
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
			return null;
		}
		
		List<PrixDto> listPrixDtos = new ArrayList<PrixDto>();
		
		logger.info(Messages.MSG_PRIX_LIST);		

		logger.info(Messages.MSG_INVOCATION_BD);
		
		List<HistoCarb> caburantList = histoCarbDao.findPrixStationDate(stationDto.convertir(), date);
		
		for(HistoCarb histoCarb : caburantList) {
			listPrixDtos.add(new PrixDto(histoCarb));
		}
		
		
		return listPrixDtos;
	}
	
	


	

}
