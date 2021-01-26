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
 * On hérite de la classe ServicesSoapConfig pour valider la clé d'authentification.
 * Cette classe, on l'a créée nous même.
 * L'implementation de l'interface est pour le but de respecter la synthaxe JAX WS indiquée
 * Dans sa documentation, ref: https://www.baeldung.com/jax-ws.
 * @author ESSABRI & SARJANE
 *
 */
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.UtilisateurCrud")
public class UtilisateurCrudImpl extends ServicesSoapConfig  implements UtilisateurCrud {
	
	private static final Logger logger = Log.getLogger(UtilisateurCrudImpl.class);
	
	// Propriété injectée par JAX-WS et utilisée pour récupérer les headers de la requête.
	// Ici, utilisée essentiellement pour récupérer la clé d'authentification.
	@Resource
    WebServiceContext webServiceContext;
	
	UtilisateurDao utilisateurDao = new UtilisateurDao();

	@Override
	public UtilisateurDto validerUtilisateur(String nom, String motDePasse) {
		// On vérifie la clé d'authentification
		if(! verifierAuthKey(webServiceContext)) {
			
			logger.info(Messages.ERR_ACCES_NON_AUTORISE);
			
			
			// On peut modifier cette réponse après pour renvoyer un code d'erreur.
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
