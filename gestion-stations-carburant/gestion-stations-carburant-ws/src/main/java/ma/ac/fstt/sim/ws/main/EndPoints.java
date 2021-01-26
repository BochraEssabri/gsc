package ma.ac.fstt.sim.ws.main;

import javax.xml.ws.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.ws.CarburantCrudImpl;
import ma.ac.fstt.sim.ws.Crud;
import ma.ac.fstt.sim.ws.PrixCrudImpl;
import ma.ac.fstt.sim.ws.StationCrudImpl;
import ma.ac.fstt.sim.ws.UtilisateurCrudImpl;
public class EndPoints {
	
	/**
	 * Objet qui permet de tracer les messages lors de l'exécution de l'application.
	 */
	private static final Logger logger = Log.getLogger(EndPoints.class);
	
	public static void main(String[] args) {
		try {
			
			logger.info(Messages.MSG_DEMARRAGE_APPLI);
			
			logger.info(Messages.MSG_PUBLICATION_SERVICES);

			
			
			final String URL_CARBURANTS = Messages.LOCAL_HOST +  Messages.SMB_SLASH + Messages.PATH_CARBURANTS;
			logger.info(Messages.MSG_PUBLICATION + URL_CARBURANTS);
			Endpoint.publish(URL_CARBURANTS, new CarburantCrudImpl() );
			

			final String URL_STATIONS   = Messages.LOCAL_HOST +  Messages.SMB_SLASH + Messages.PATH_STATIONS;
			logger.info(Messages.MSG_PUBLICATION + URL_STATIONS);
			
			Endpoint.publish(URL_STATIONS, new StationCrudImpl());

			final String URL_PRIX		= Messages.LOCAL_HOST +  Messages.SMB_SLASH + Messages.PATH_PRIX;
			logger.info(Messages.MSG_PUBLICATION + URL_PRIX);
			Endpoint.publish(URL_PRIX, new PrixCrudImpl() );

			final String URL_AUTH		= Messages.LOCAL_HOST +  Messages.SMB_SLASH + Messages.PATH_AUTH;
			logger.info(Messages.MSG_PUBLICATION + URL_AUTH);
			Endpoint.publish(URL_AUTH, new UtilisateurCrudImpl() );
			
			System.out.println("Services SOAP disponibles!");
			
			/*
			Carburant car = new CarburantCrudImpl().findCarburantById(1);
			
			logger.debug(car.getNomCarburant());*/
			
			/**
			 * Testes utils en cas de problème avec la dépendence JPA
			 * Dao dao = new CarburantDao();
		     * System.out.println(dao.findById(1).getDescription());
			
			*/
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
