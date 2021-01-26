package ma.ac.fstt.sim.ws;


import javax.annotation.Resource;
import javax.jws.*;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.dao.UtilisateurDao;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;
import ma.ac.fstt.sim.ws.config.ServicesSoapConfig;
import ma.ac.fstt.sim.ws.dto.UtilisateurDto;

/**
 * On h�rite de la classe ServicesSoapConfig pour valider la cl� d'authentification.
 * Cette classe, on l'a cr��e nous m�me.
 * L'implementation de l'interface est pour le but de respecter la synthaxe JAX WS indiqu�e
 * Dans sa documentation, ref: https://www.baeldung.com/jax-ws.
 * @author ESSABRI & SARJANE
 *
 */
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.UtilisateurCrud")
public class UtilisateurCrudImpl extends ServicesSoapConfig  implements UtilisateurCrud {
	
	private static final Logger logger = Log.getLogger(UtilisateurCrudImpl.class);
	
	// Propri�t� inject�e par JAX-WS et utilis�e pour r�cup�rer les headers de la requ�te.
	// Ici, utilis�e essentiellement pour r�cup�rer la cl� d'authentification.
	@Resource
    WebServiceContext webServiceContext;
	
	UtilisateurDao utilisateurDao = new UtilisateurDao();

	@Override
	public UtilisateurDto validerUtilisateur(String nom, String motDePasse) {
		// On v�rifie la cl� d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			
			// On peut modifier cette r�ponse apr�s pour renvoyer un code d'erreur.
			return null;
		}
		
		logger.info(Messages.MSG_VALIDATION_UTILISATEUR + nom);
		
		
		if(	  nom == null || nom.isEmpty()
		   || motDePasse == null || motDePasse.isEmpty()) {
			
			return null;
		}
		
		Utilisateur utilisateur = utilisateurDao.findByNom(nom);
		
		if(utilisateur != null && motDePasse.equals(utilisateur.getMotDePasse()))
		{
			logger.info(Messages.MSG_VALIDATION_SUCCESS + nom);
			
			return new UtilisateurDto(utilisateur);
		}
		
		logger.info(Messages.MSG_VALIDATION_ERRONEE + nom);
		
		return null;
	}
	

}
