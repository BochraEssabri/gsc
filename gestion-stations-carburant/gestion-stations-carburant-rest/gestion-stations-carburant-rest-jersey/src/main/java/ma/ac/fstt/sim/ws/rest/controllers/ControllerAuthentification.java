package ma.ac.fstt.sim.ws.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.exceptions.MotDePasseNonModifiableException;
import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.ws.rest.beans.Utilisateur;
import ma.ac.fstt.sim.ws.rest.configuration.AuthentificationUtilisateurs;
import ma.ac.fstt.sim.ws.rest.configuration.Utils;

@Path("/")
public class ControllerAuthentification {
	private static final Logger logger = Log.getLogger(ControllerAuthentification.class);
	
	
	@Context
    private ResourceContext resourceContext;
	
	
	
	@Context
	UriInfo uri;
	
	

	@PermitAll
	@POST
	@Path("/connexion")
	@Produces(MediaType.TEXT_HTML)
	public Response seConnecter(@FormParam("nom-utilisateur") String nomUtilisateur,
							@FormParam("mot-de-passe") String motDePasse,
							@Context HttpServletRequest request
			) {
		
		Utilisateur utilisateur;
		utilisateur = AuthentificationUtilisateurs.getInstance()
									.authentifierUtilisateur(nomUtilisateur, motDePasse);
		
		if( utilisateur != null && utilisateur.validerParSoap()) {
			
			request.getSession().setAttribute(Utils.USER_SESSION, utilisateur);
				
		}
		else {
			request.getSession().setAttribute(Utils.AUTH_KO, true);
		}
		
		// redirection vers la page principale.
		
		URI location;
		
		try {
			location = new URI(uri.getBaseUri().toString());
			
			logger.info(Messages.MSG_CONNEXION_OK + nomUtilisateur);
			
			return Response.temporaryRedirect(location).build();
			
		} catch (URISyntaxException e) {
			logger.error(Messages.ERR_CONNEXION_KO + nomUtilisateur);
			
			logger.error(e.getStackTrace());
		}
		
		return null;
		
	}
}
