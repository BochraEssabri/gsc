package ma.ac.fstt.sim.ws.rest.configuration;

import javax.ws.rs.core.Response;

import ma.ac.fstt.sim.commun.ressoureces.Messages;

public class Utils {
	
	
	/**
	 * Le nom du paramètre contenant l'autorisation d'accès à une ressource.
	 */
	public static final String   AUTORISATION 	  = "Authorization";
    
	public static final String   AUTH_KO		  = "AUTH_KO";
	
	public static final String   USER_SESSION	  = "utilisateur";
	
	public static final String 	 USER_MODEL		  = "UTILISATEUR";
	
	public static final String 	 AUTH_MODEL		  = "AUTHENTIFICATION";
	
	public static final String 	 AUTHENTIFICATION = "Basic";
    
    public static final Response ACCESS_REFFUSE   = Response.status(Response.Status.UNAUTHORIZED)
    														.entity(Messages.MSG_ACCESS_REFUSE).build();
    public static final Response ACCESS_BLOQUE 	  = Response.status(Response.Status.FORBIDDEN)
    														.entity(Messages.MSG_ACCESS_IMPOSSIBLE).build();
    public static final String 	 ROLE_UTILISATEUR = "UTILISATEUR";
}
