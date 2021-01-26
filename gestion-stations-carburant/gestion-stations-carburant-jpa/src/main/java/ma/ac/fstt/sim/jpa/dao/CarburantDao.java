package ma.ac.fstt.sim.jpa.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;
import ma.ac.fstt.sim.jpa.persistence.PersistenceManager;

public class CarburantDao implements Dao<Carburant> {

	private static final Logger logger = LogManager.getLogger(CarburantDao.class);
	
	// Id des carburants Habutuels
	public static final int CARBURANT_ESSENCE = 1;
	public static final int CARBURANT_GAZOLE = 2;
	
	
	/**
	 *  on récupère un manager pour commencer une transaction avec la BD.
	 *  On fait pas manager.close pour laisser Hibernate pour charger les dependances.
	 *  qu'on a déclarée Lazy quand on en aura besoin.
	 *  le close sera appelé à la fin de l'execution de l'application.
	 *  ref: https://stackoverflow.com/questions/11794633/trying-to-load-lazy-relationships-after-entitymanager-closes
	 *  
	 */
	private EntityManager manager = PersistenceManager.getInstance().getManager();
	
	public CarburantDao() {
		
	}
	

	@Override
	public Carburant find(final int id) {

		Carburant resultat = null;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			resultat = manager.find(Carburant.class, id);

			logger.info(Messages.MSG_TESANSACTION_OK);

		} catch (Exception ex) {
			logger.error(Messages.ERR_TESANSACTION_KO);

			if (transaction != null) {
				transaction.rollback();
			}

			logger.error(ex.getStackTrace());
		} finally {
			transaction.commit();
			// Pour laisser la possibilité de récupérer les dépendences.
			//manager.close();
			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);

		}

		return resultat;
	}

	@Override
	public List<Carburant> getAll() {

		List<Carburant> resultat = null;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			/*
			 * Pour récupérer tous les carburants ref:
			 * https://stackoverflow.com/questions/24572092/using-java-generics-for-jpa-
			 * findall-query-with-where-clause
			 */

			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

			CriteriaQuery<Carburant> criteriaQuery = criteriaBuilder.createQuery(Carburant.class);

			Root<Carburant> root = criteriaQuery.from(Carburant.class);

			CriteriaQuery<Carburant> all = criteriaQuery.select(root);

			TypedQuery<Carburant> tousLesElements = manager.createQuery(all);

			resultat = tousLesElements.getResultList();

			logger.info(Messages.MSG_TESANSACTION_OK);

		} catch (Exception ex) {
			logger.error(Messages.ERR_TESANSACTION_KO);

			if (transaction != null) {
				transaction.rollback();
			}

			logger.error(ex.getStackTrace());
		} finally {
			transaction.commit();
			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);
		}

		return resultat;

	}

	@Override
	public boolean create(Carburant carburant) {

		boolean resultat = false;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			manager.persist(carburant);

			resultat = true;

			logger.info(Messages.MSG_TESANSACTION_OK);

		} catch (Exception ex) {
			logger.error(Messages.ERR_TESANSACTION_KO);

			if (transaction != null) {
				transaction.rollback();
			}

			logger.error(ex.getStackTrace());
		} finally {
			transaction.commit();
			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);
		}
		
		return resultat;
	}

	@Override
	public boolean update(Carburant carburant) {

		boolean resultat = false;

		if (carburant == null || carburant.getIdCarburant() <= 0) {
			return resultat;
		}

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		Carburant ancienCarburant = manager.find(Carburant.class, carburant.getIdCarburant());

		if (ancienCarburant == null) {
			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);

			return resultat;
		}

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			ancienCarburant.setDescription(carburant.getDescription());

			ancienCarburant.setNomCarburant(carburant.getNomCarburant());

			if (carburant.getHistoCarbs() != null && !carburant.getHistoCarbs().isEmpty()) {
				ancienCarburant.setHistoCarbs(carburant.getHistoCarbs());
			}

			resultat = true;

			logger.info(Messages.MSG_TESANSACTION_OK);

		} catch (Exception ex) {
			logger.error(Messages.ERR_TESANSACTION_KO);

			if (transaction != null) {
				transaction.rollback();
			}

			logger.error(ex.getStackTrace());
		} finally {
			transaction.commit();

			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);
		}

		return resultat;
	}

	@Override
	public boolean delete(int id) {

		boolean resultat = false;

		if (id <= 0) {
			return resultat;
		}

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();
		logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

		try {

			Carburant ancienCarburant = manager.find(Carburant.class, id);

			manager.remove(ancienCarburant);

			resultat = true;

			logger.info(Messages.MSG_TESANSACTION_OK);

		} catch (Exception ex) {
			logger.error(Messages.ERR_TESANSACTION_KO);

			if (transaction != null) {
				transaction.rollback();
			}

			logger.error(ex.getStackTrace());
		} finally {
			transaction.commit();

			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);
		}

		return resultat;
	}


	@Override
	/**
	 * Cette opérantion n'est pas disponible.
	 */
	public List<HistoCarb> findPrixStationDate(Station station, String Date) {
		
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		
		return null;
	}


	@Override
	/**
	 * Cette opérantion n'est pas disponible.
	 */
	public Utilisateur findByNom(String nom) {
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		return null;
	}

}
