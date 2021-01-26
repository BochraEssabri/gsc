package ma.ac.fstt.sim.ws.rest.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.servlet.ServletProperties;

public class HtmlConfig extends ResourceConfig/*extends ResourceConfig*/
{
	public HtmlConfig() {
		
		// Configuration des vues : controller et localisation des pages htmls
		packages("ma.ac.fstt.sim.ws.rest.controllers.ihm");
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/pages");
        register(JspMvcFeature.class);
        register(FiltreAuthentification.class);
        
        // Pour activer le contexte de la sécurité.
        //register(RolesAllowedDynamicFeature.class);
        
        //Pour servir le contenu statique: js, css ...
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
}
