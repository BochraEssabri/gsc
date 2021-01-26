package ma.ac.fstt.sim.ws.rest.configuration;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import ma.ac.fstt.sim.ws.rest.beans.Utilisateur;

public class SecuriteConfig implements SecurityContext{
	
	private Utilisateur utilisateur;
	
	/**
	 * Scheme en anglais: http, https?
	 * La méthode de communication avec nos services rest.
	 */
    private String 		Scheme;

    
    public SecuriteConfig() {
    	
    }
    
    public SecuriteConfig(Utilisateur utilisateur, String methodeAuthentification) {
    	this.utilisateur 	= utilisateur;
    	this.Scheme 		= methodeAuthentification;
    }
    
	
	public Principal getUserPrincipal() {
		return utilisateur;
	}
	
	public void setUserPrincipal(Principal utilisateur) {
		utilisateur = (Utilisateur) utilisateur;
	}

	@Override
	public boolean isUserInRole(String role) {
		return utilisateur.getRole()!= null ? utilisateur.getRole().contains(role)
											: false;
	}

	@Override
	public boolean isSecure() {
		return "https".equals(this.Scheme);
	}

	@Override
	/**
	 * La méthode d'authentification choisie.
	 */
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

}
