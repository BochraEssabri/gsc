package ma.ac.fstt.sim.ws.rest.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.ws.rest.beans.Utilisateur;
import ma.ac.fstt.sim.ws.rest.configuration.Utils;



@Path("/")
public class ControllerPages {
	// sera utilisé en javascript sur la page jsp.
	private static final Logger logger = Log.getLogger(ControllerPages.class);
	
	@Context
	UriInfo uri;
	 
	@Context
	HttpServletRequest request;
	
	private static final String libelles = 
									  "{"
									+ "\"carburant\": {"
									+ 	"\"idCarburant\":\"Id Carburant\","
									+ 	"\"nomCarburant\":\"Nom du Carburant\","
									+ 	"\"description\" : \"Informations sur le Carburant\"}"
									+ ","
									+ ""
									+ 	"\"station\": {"
									+ 		"\"idStation\": \"Id Station\", " + 
									"		\"nomStation\": \"Nom de la Station\", " + 
									"		\"adresse\" : \"Adresse\"," + 
									"		\"ville\"	: \"ville\""
									+ 				"}"
									+ 	","
									+ ""
									+ 	"\"prix\":{"
									+ 		"\"date\" : \"Date\", "
									+ 		"\"prix\" : \"Prix\""
									+ 		"}"
									+ 	"}";
	

									
	public ControllerPages() {
		super();
	}
	

	/**
	 * Méthode qui permet de renvoyer la page JSP de l'application.
	 * 
	 * @param request permet de gérer les variables de l'application telle que la session.<br/>
	 * 		  Ref: https://stackoverflow.com/questions/11831042/restful-how-to-get-access-to-httpsession-inside-the-service-class
	 * @return
	 */
	@PermitAll
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		Map<String, String> model = new HashMap<>();
		
		final String URL_ROOT = uri.getBaseUri().toString();

		
		/**
		 * test
		 */
		/*
		Utilisateur user = new Utilisateur();
		user.setName("ADMIN");
		try {
			user.setPassword("ADMIN");
		} catch (MotDePasseNonModifiableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		final Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute(Utils.USER_SESSION);
		
		logger.info("URL de l'application " + URL_ROOT);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAjourdhui = new Date();
		String date = dateFormat.format(dateAjourdhui);
		
		model.put("date", date);
		
		// Les libellés pour afficher les listes des différentes données.
		model.put("LIBELLES", libelles);
		
		model.put("URL_ROOT", URL_ROOT);

		/**
		 * On encode le nom utilisateur et le mot de passe
		 * Format avant encodage : utilisateur:motDePasse
		 * Ref: https://www.baeldung.com/java-base64-encode-and-decode
		 */
		if(utilisateurConnecte != null) {
			
			final String AUTHENTIFICATION = utilisateurConnecte.recupererAuthentificationBase64();
			
			// Pour les communications JS avec les WS Rest et l'affichage de la page authentification.
			model.put(Utils.AUTH_MODEL, AUTHENTIFICATION);
			

			model.put(Utils.USER_MODEL, utilisateurConnecte.getName());
		
		}
		else if(request.getSession().getAttribute("AUTH_KO") != null) {
			 
				boolean AuthentificationKo = (boolean) request.getSession().getAttribute("AUTH_KO");
				
				if(AuthentificationKo) {
					model.put(Utils.AUTH_KO, Messages.ERR_AUTH_KO);
				}	
		}
		
		return new Viewable("/gsc", model);
		
		
	}
	
	/**
	 * Après authentification (controllerAuthentification )
	 * On redirige la requête ( méthode de type POST ici ) avant
	 * de revenir vers index (qui exige la méthode GET)
	 * 
	 * ControllerAuthentification.seConnecter() ne peut 
	 * set rediriger vers index() de type Get que via 
	 * l'intermédiaire de cette méthiode.
	 * @param request
	 * @return
	 */
	@PermitAll
	@POST
	@Produces(MediaType.TEXT_HTML)
	public Viewable redirection() {
		return index();
		
	}
	
	@PermitAll
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/deconnexion")
	public Viewable deconnexion() {
		request.getSession().removeAttribute("utilisateur");
		
		logger.info("Déconnexion OK!");
		return index();
	}
}
