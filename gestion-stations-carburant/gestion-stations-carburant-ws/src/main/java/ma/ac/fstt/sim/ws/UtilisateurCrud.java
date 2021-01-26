package ma.ac.fstt.sim.ws;


import javax.jws.*;

import ma.ac.fstt.sim.ws.dto.UtilisateurDto;

@WebService
public interface UtilisateurCrud {
	
	@WebMethod
	public UtilisateurDto validerUtilisateur(String nom, String motDePasse);
	

	
	
}
