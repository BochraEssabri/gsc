package ma.ac.fstt.sim.ws.config;

import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import ma.ac.fstt.sim.commun.ressoureces.ProjectConfiguration;

public class ServicesSoapConfig {
	
	/**
	 * Généré à partir de la plateforme https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
	 * A noter qu'on peut faire mieux en developpant un mécanisme pour la génération dynamique
	 * de cette clé.
	 */
	private final String SOAP_AUTH_KEY = "t6w9z$C&F)J@NcRfUjXn2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWm";

	/**
	 * Récupère et valide la clé d'authentification reçue à partir de chaque requête SOAP.
	 * @param webServiceContext
	 * @return
	 */
	public boolean verifierAuthKey(WebServiceContext webServiceContext) {
		
		MessageContext messageContext = webServiceContext.getMessageContext();
		
		Map<String, List> httpHeaders = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		
		if(httpHeaders == null ) {
			return false;
		}
		
		List soapAuthKey = (List) httpHeaders.get(ProjectConfiguration.SOAP_AUTH_KEY);
		
		return soapAuthKey != null && SOAP_AUTH_KEY.equals(soapAuthKey.get(0));
	}
	
}
