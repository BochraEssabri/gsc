package ma.ac.fstt.sim.ws.rest.configuration;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;

import ma.ac.fstt.sim.commun.exceptions.MotDePasseNonModifiableException;
import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.ws.rest.beans.Utilisateur;

/**
 * Applique un filtre sur les requêtes reçues 
 * par l'application pour vérifier l'authentification
 * Cela fait partie de la sécurité de l'application.
 * Pour exécuter ce filtre en priorité 
 * Car on a activé RolesAllowedDynamicFeature de garder les infos de l'utilisateur après l'authentification.
 * Ref: https://stackoverflow.com/questions/17068528/authorization-with-rolesalloweddynamicfeature-and-jersey
 *
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FiltreAuthentification implements ContainerRequestFilter{
	private static final Logger logger = Log.getLogger(FiltreAuthentification.class);
	
	/**
	 * Contient les infos sur la requête actuelle
	 * Injectée automatiquement par jaxRS via @Context
	 */
	@Context
    private ResourceInfo informationRessource;
	@Context 
	private HttpServletRequest request;

    Utilisateur utilisateurBean;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		/* On a plus besoin de ça pour l'instant car on utilise la session.
		 * String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
		 SecurityContext contexteSecurite = requestContext.getSecurityContext();
		 contexteSecurite = new SecuriteConfig(utilisateurBean, scheme);
		requestContext.setSecurityContext(contexteSecurite);
		*/

		
		
		//On récupère la méthode
		Method methodeAppelee = informationRessource.getResourceMethod();
		
		
		// On teste si la méthode est autorisée sans permissions
		if( ! methodeAppelee.isAnnotationPresent(PermitAll.class)) {
			
			// Cas d'accès réfusé
			if( methodeAppelee.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(Messages.MSG_ACCESS_IMPOSSIBLE).build());
				return;
			}
			
			// Si l'accès n'est pas bloqué
			MultivaluedMap<String, String> parametresHeader = requestContext.getHeaders();
			
			//Fetch authorization header
            final List<String> autorisationParametre = parametresHeader.get(Utils.AUTORISATION);
             
            // Cas d'absence d'autorisation
            if(autorisationParametre == null || autorisationParametre.isEmpty())
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(Messages.MSG_ACCESS_REFUSE).build());
                return;
            }
            
    		

        	// On récupère le paramètre crypté (utilisateur et mot de passe).
            final String autorisationCryptee 		= autorisationParametre.get(0).replaceFirst(Utils.AUTHENTIFICATION + " ", "");
             
            // Décryptage des infos d'authentification
            String authentificationDecryptee 	  	= new String(Base64.decode(autorisationCryptee.getBytes()));;
 
            // On divise l'utilisateur du mot de passe
            final StringTokenizer stringtokenizer 	= new StringTokenizer(authentificationDecryptee, ":");
            final String nomUtilisateur				= stringtokenizer.hasMoreElements()? stringtokenizer.nextToken() : "";
            final String motDePasse					= stringtokenizer.hasMoreElements()? stringtokenizer.nextToken() : "";

            
    		// Vérification des informations récupérées.
            logger.info(Messages.MSG_VERIF_PERSMISSIONS);
            
            if(methodeAppelee.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation 		= methodeAppelee.getAnnotation(RolesAllowed.class);
                
                Set<String> listeRoles 				= new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                 
                //Is user valid?
                if( ! isUtilisateurAutorise(nomUtilisateur, motDePasse, listeRoles))
                {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(Messages.MSG_ACCESS_REFUSE).build());
                    return;
                }
            }
            
      
		}

		
		logger.warn(" Filter appliqué");
	}
	
	/**
	 * Permet de vérifier les droits de l'utilisateur fournie dans une requête 
	 * pour accéder à une ressource.
	 * @param nomUtilisateur
	 * @param MDPasse
	 * @param listeRolesRessource
	 * @return
	 */
    private boolean isUtilisateurAutorise(final String nomUtilisateur, final String MDPasse, final Set<String> listeRolesRessource)
    {
        
		// On récupère les infos d'authentification de la session si l'utilisateur est déjà 
        // été authentifié sur l'application via l'interface de connexion.
		// Dans ce cas, on fait la vérification sur la session directement pour eviter de 
		// communiquer avec la base de données requête, la chose qui baisse les performances.
		utilisateurBean = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		
        // Dans le cas d'absence d'authentificatio, on sollicite le service SOAP
		// qui nous fournit les valeurs enregistées en basede données.
        if(utilisateurBean == null || utilisateurBean.getName() == null 
        						   || !utilisateurBean.getName().equals(nomUtilisateur)) {
        	utilisateurBean = AuthentificationUtilisateurs.getInstance()
					.authentifierUtilisateur(nomUtilisateur, MDPasse);
        }
        
        // Si aucun des utilisateurs n'a été trouvé dans la BD
        // ça veut SOAP a retourné null, ce qui signifie que les informations
        // saisie ne sont pas correctes.
        if(utilisateurBean == null) {
        	return false;
        }
       
        
        // Si on trouve un utilisateur dans la session ou dans la BD, on fait la vérification.
        return     utilisateurBean.valider(nomUtilisateur, MDPasse, new ArrayList<String>(listeRolesRessource))
        		|| (   utilisateurBean.validerParSoap() 
        			&& utilisateurBean.getRole() != null
        			&& utilisateurBean.validerRoles(new ArrayList<String>(listeRolesRessource)));

    }

}
