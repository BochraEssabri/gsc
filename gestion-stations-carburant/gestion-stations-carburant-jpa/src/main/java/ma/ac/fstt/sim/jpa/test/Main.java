package ma.ac.fstt.sim.jpa.test;
import java.util.List;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.jpa.dao.CarburantDao;
import ma.ac.fstt.sim.jpa.dao.Dao;
import ma.ac.fstt.sim.jpa.dao.HistoCarbDao;
import ma.ac.fstt.sim.jpa.dao.StationDao;
import ma.ac.fstt.sim.jpa.dao.UtilisateurDao;
import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;

public class Main {
	private static final Logger logger = Log.getLogger(Main.class);
	
	public static void main(String[] args) {
		Dao daoCarburant = new CarburantDao();
		Dao daoStation = new StationDao();
		Dao daoHistoCarb = new HistoCarbDao();
		Dao daoUtilisateur = new UtilisateurDao();
		
		//testerCarburant(daoCarburant);
		
		//testerStation(daoStation);
		
		//testerHistoCarb(daoHistoCarb);
		
		testUtilisateur(daoUtilisateur);
		
		
	}
	
	private static void testUtilisateur(Dao daoUtilisateur) {
		Utilisateur utilisateur = daoUtilisateur.findByNom("ADMIN");
		
		logger.info("Utilisateur: " + utilisateur.getNomUtilisateur());
		
	}

	public static void testerCarburant(Dao dao) {
		int idTest = 6;
		// creation
		/* 
		  Carburant carburant = new Carburant();
		  
		  carburant.setDescription("nouvelle description");
		  carburant.setNomCarburant("Electricité");
		  boolean isCrea = dao.create(carburant);
		  logger.info("================================== Resultat creation ================================");
		  logger.info("Creation: "+isCrea);
		 */
		
		
		// Modification
		
		/*
		Carburant carburant = dao.find(idTest);
		
		logger.info("================================== Resultat modification ================================");
		logger.info("Avant Modification: ");
		logger.info("-> nom:"+carburant.getNomCarburant());
		logger.info("-> description:"+carburant.getDescription());
		
		carburant.setNomCarburant(carburant.getNomCarburant()+" modifié");
		boolean isModif = dao.update(carburant);
		
		logger.info("Après Modification: "+ isModif);
		logger.info("-> nom:"+carburant.getNomCarburant());
		logger.info("-> description:"+carburant.getDescription());
		logger.info("====================================================================================");
		*/
		
		
		// Tout récupérer
		/*
		List<Carburant> liste = dao.getAll();
		
		logger.info("================================== Resultat modification ================================");
		
		for(Carburant carburant : liste) {
			logger.info("====================");
			logger.info("-> id:"+carburant.getIdCarburant());
			logger.info("-> nom:"+carburant.getNomCarburant());
			logger.info("-> description:"+carburant.getDescription());
			
		}

		logger.info("====================");
		
		logger.info("====================================================================================");
		*/
/*
		boolean isDel = false;
		logger.info("================================== Resultat suppression ================================");
		
		isDel = dao.delete(idTest);
		logger.info("Suppression: "+isDel);
		
		logger.info("====================================================================================");
		*/
	}
	
	public static void testerStation(Dao dao) {
		int idTest = 5;
		// creation
		 
		/*
		  Station station = new Station();
		  
		  station.setAdresse("nouvelle adresse");
		  station.setNomStation("Afriquia");
		  station.setVille("Rabat");
		  boolean isCrea = dao.create(station);
		  logger.info("================================== Resultat creation ================================");
		  logger.info("Creation: "+isCrea);
		 */
		
		
		// Modification
		
		/*
		Station station =  (Station) dao.find(idTest);
		
		logger.info("================================== Resultat modification ================================");
		logger.info("Avant Modification: ");
		logger.info("-> nom:"+station.getNomStation());
		logger.info("-> adresse:"+station.getAdresse());
		
		station.setNomStation(station.getNomStation()+" modifié");
		boolean isModif = dao.update(station);
		
		logger.info("Après Modification: "+ isModif);
		logger.info("-> nom:"+station.getNomStation());
		logger.info("-> adresse:"+station.getAdresse());
		logger.info("====================================================================================");
		*/
		
		
		// Tout récupérer
		/*
		List<Station> liste = dao.getAll();
		
		logger.info("================================== Resultat modification ================================");
		
		for(Station station : liste) {
			logger.info("====================");
			logger.info("-> id:"+station.getIdStation());
			logger.info("-> nom:"+station.getNomStation());
			logger.info("-> adresse:"+station.getAdresse());
			
		}

		logger.info("====================");
		
		logger.info("====================================================================================");
		*/
		
		/*
		boolean isDel = false;
		logger.info("================================== Resultat suppression ================================");
		
		isDel = dao.delete(idTest);
		logger.info("Suppression: "+isDel);
		
		logger.info("====================================================================================");
		*/
	}
	
	public static void testerHistoCarb(Dao dao) {
		int test = 45;
		
		// creation
		 
		/*
		  HistoCarb histoCarb = new HistoCarb();
		  
		  histoCarb.setDate("2020-12-18");
		  histoCarb.setIdCarburant(3);
		  histoCarb.setIdStation(3);
		  histoCarb.setPrix(8.30);
		  boolean isCrea = dao.create(histoCarb);
		  logger.info("================================== Resultat creation ================================");
		  logger.info("Creation: "+isCrea);
		 */
		
		
		// Modification
		/**
		 * @deprecated
		 */
		/*
		HistoCarb histoCarb =  (HistoCarb) dao.find(test);
		
		logger.info("================================== Resultat modification ================================");
		logger.info("Avant Modification: ");
		logger.info("-> id:"+histoCarb.getIdHistoCarb());
		logger.info("-> prix:"+histoCarb.getPrix());
		
		histoCarb.setDate("2020-12-19");
		boolean isModif = dao.update(histoCarb);
		
		logger.info("Modification: "+ isModif);
		logger.info("-> date:"+histoCarb.getDate());
		logger.info("-> date:"+histoCarb.getDate());
		logger.info("====================================================================================");
		*/
		
		// test relations
		/*
		HistoCarb histoCarb =  (HistoCarb) dao.find(test);
		
		logger.info("================================== Resultat modification ================================");
		logger.info("Avant Modification: ");
		logger.info("-> id:"+histoCarb.getIdHistoCarb());
		logger.info("-> prix:"+histoCarb.getPrix());

		
		logger.info("-> date:"+histoCarb.getDate());
		logger.info("-> carburant:"+histoCarb.getCarburant().getNomCarburant());
		logger.info("-> station:"+histoCarb.getStation().getNomStation());
		logger.info("====================================================================================");
		
		*/
		
		// Tout récupérer
		/*
		List<HistoCarb> liste = dao.getAll();
		
		logger.info("================================== Resultat modification ================================");
		
		for(HistoCarb histoCarb : liste) {
			logger.info("====================");
			logger.info("-> id:"+histoCarb.getIdHistoCarb());
			logger.info("-> nom:"+histoCarb.getNomHistoCarb());
			logger.info("-> adresse:"+histoCarb.getAdresse());
			
		}

		logger.info("====================");
		
		logger.info("====================================================================================");
		*/
		
		/*
		boolean isDel = false;
		logger.info("================================== Resultat suppression ================================");
		
		isDel = dao.delete(idTest);
		logger.info("Suppression: "+isDel);
		
		logger.info("====================================================================================");
		*/
		
		Station station = new Station();
		station.setIdStation(1); 
		List<HistoCarb> liste = dao.findPrixStationDate(station , "2020-12-14");
		String description = liste.get(0).getCarburant().getDescription();
		logger.info("carburant" + description);
	}

}
