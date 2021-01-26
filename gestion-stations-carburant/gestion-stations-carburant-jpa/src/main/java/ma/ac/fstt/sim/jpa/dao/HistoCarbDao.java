package ma.ac.fstt.sim.jpa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;
import ma.ac.fstt.sim.jpa.persistence.PersistenceManager;

public class HistoCarbDao implements Dao<HistoCarb> {

	private static final Logger logger = Log.getLogger(HistoCarbDao.class);

	/**
	 *  on récupère un manager pour commencer une transaction avec la BD.
	 *  On fait pas manager.close pour laisser Hibernate pour charger les dependances.
	 *  qu'on a déclarée Lazy quand on en aura besoin.
	 *  le close sera appelé à la fin de l'execution de l'application.
	 *  ref: https://stackoverflow.com/questions/11794633/trying-to-load-lazy-relationships-after-entitymanager-closes
	 *  
	 */
	private EntityManager manager = PersistenceManager.getInstance().getManager();
	
	
	public HistoCarbDao() {

	}

	@Override
	public HistoCarb find(final int id) {

		HistoCarb resultat = null;



		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			resultat = manager.find(HistoCarb.class, id);

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
	public List<HistoCarb> getAll() {

		List<HistoCarb> resultat = null;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			/*
			 * Pour récupérer tous les histoCarbs ref:
			 * https://stackoverflow.com/questions/24572092/using-java-generics-for-jpa-
			 * findall-query-with-where-clause
			 */

			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

			CriteriaQuery<HistoCarb> criteriaQuery = criteriaBuilder.createQuery(HistoCarb.class);

			Root<HistoCarb> root = criteriaQuery.from(HistoCarb.class);

			CriteriaQuery<HistoCarb> all = criteriaQuery.select(root);

			TypedQuery<HistoCarb> tousLesElements = manager.createQuery(all);

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
	
	/**
	 * Retourne les prix des deux carburants habituels gazole et essence pour une date et une station données.
	 * @param station
	 * @param date
	 * 
	 * @return List
	 */
	public List<HistoCarb> findPrixStationDate(Station station, String date) {
		
		List<HistoCarb> resultat = new ArrayList<HistoCarb>();
		
		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();
		
		// Refs pour utilisation de where:
		/// https://www.objectdb.com/java/jpa/query/jpql/from
		/// https://stackoverflow.com/questions/14977018/jpa-how-to-get-entity-based-on-field-value-other-than-id
		try {
			transaction.begin();
		
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
	
			CriteriaQuery<HistoCarb> criteriaQuery = criteriaBuilder.createQuery(HistoCarb.class);
			
			Root<HistoCarb> root = criteriaQuery.from(HistoCarb.class);
			
			// Par défaut on a lazy pour ne pas baisser les performances
			// Ici, on a imposé le eager car on en a besoin des deux relations.
			root.fetch("carburant");
			root.fetch("station");
			
			
			CriteriaQuery<HistoCarb> resultatQuery = criteriaQuery.select(root).where(
														criteriaBuilder.equal(root.get("station"),station.getIdStation()),
														criteriaBuilder.equal(root.get("date"),date),
														criteriaBuilder.or(
																criteriaBuilder.equal(root.get("carburant"),CarburantDao.CARBURANT_ESSENCE),
																criteriaBuilder.equal(root.get("carburant"),CarburantDao.CARBURANT_GAZOLE)
																)
														
														);
			
			TypedQuery<HistoCarb> resultatTypedQuery = manager.createQuery(resultatQuery);
			
			resultat = resultatTypedQuery.getResultList();
		
		}
		catch(Exception e) {
			logger.error(e);
		}
		finally {
			if(transaction.isActive()) {
				transaction.commit();
			}
			
		}
		
		return resultat;
	}

	@Override
	public boolean create(HistoCarb histoCarb) {

		boolean resultat = false;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			manager.persist(histoCarb);

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
	public boolean update(HistoCarb histoCarb) {

		boolean resultat = false;

		if (histoCarb == null || histoCarb.getIdHistoCarb() <= 0
				|| !histoCarb.getDate().matches(HistoCarb.DATE_REG_EX)) {
			return resultat;
		}

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		HistoCarb ancienHistoCarb = manager.find(HistoCarb.class, histoCarb.getIdHistoCarb());

		if (ancienHistoCarb == null) {
			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);

			return resultat;
		}

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			/*
			 * On modifie les relations.
			 */
			if (histoCarb.getCarburant() != null) {
				Carburant carburant = new Carburant();
				carburant.setIdCarburant(histoCarb.getCarburant().getIdCarburant());

				ancienHistoCarb.setCarburant(carburant);
			}

			if (histoCarb.getStation() != null) {
				Station station = new Station();
				station.setIdStation(histoCarb.getStation().getIdStation());

				ancienHistoCarb.setStation(station);
			}

			ancienHistoCarb.setDate(histoCarb.getDate());

			ancienHistoCarb.setPrix(histoCarb.getPrix());

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

			HistoCarb ancienHistoCarb = manager.find(HistoCarb.class, id);

			manager.remove(ancienHistoCarb);

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
	
	/**
	 * Cette opérantion n'est pas disponible.
	 */
	@Override
	public Utilisateur findByNom(String nom) {
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		return null;
	}

}
