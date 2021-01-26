package ma.ac.fstt.sim.ws.rest.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

import ma.ac.fstt.sim.commun.ressoureces.ProjectConfiguration;

public class AuthenificationSoap {
	
	private static AuthenificationSoap instance = new AuthenificationSoap();
	
	private final String SOAP_AUTH_KEY = "t6w9z$C&F)J@NcRfUjXn2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWm";
	
	
	private AuthenificationSoap() {
		
	}
	
	/**
	 * Cette méthode permet d'ajouter la clé d'authentification
	 * dans les requêtes SOAP.
	 * Ref: https://stackoverflow.com/questions/2322953/jax-ws-adding-soap-headers
	 * @param bindingProvider
	 */
	public void injecterAuthentification(WSBindingProvider bindingProvider) {
		
		Map<String, List<String>> headers = new HashMap<String, List<String>>();
		
		List<String> cle = new ArrayList<String>();
		
		cle.add(SOAP_AUTH_KEY);
		
		headers.put(ProjectConfiguration.SOAP_AUTH_KEY, cle);
		
		bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		
		bindingProvider.setOutboundHeaders(
				Headers.create(new QName(ProjectConfiguration.SOAP_AUTH_KEY), SOAP_AUTH_KEY)
		);
	}
	
	public static AuthenificationSoap getInstance() {
		return instance;
	}
}
