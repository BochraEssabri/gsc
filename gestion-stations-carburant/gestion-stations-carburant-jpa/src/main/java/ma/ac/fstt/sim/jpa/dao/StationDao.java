package ma.ac.fstt.sim.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;
import ma.ac.fstt.sim.jpa.persistence.PersistenceManager;

public class StationDao implements Dao<Station> {

	private static final Logger logger = Log.getLogger(StationDao.class);

	/**
	 *  on récupère un manager pour commencer une transaction avec la BD.
	 *  On fait pas manager.close pour laisser Hibernate pour charger les dependances.
	 *  qu'on a déclarée Lazy quand on en aura besoin.
	 *  le close sera appelé à la fin de l'execution de l'application.
	 *  ref: https://stackoverflow.com/questions/11794633/trying-to-load-lazy-relationships-after-entitymanager-closes
	 *  
	 */
	private EntityManager manager = PersistenceManager.getInstance().getManager();
	
	public StationDao() {

	}

	@Override
	public Station find(final int id) {

		Station resultat = null;


		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			resultat = manager.find(Station.class, id);

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
	public List<Station> getAll() {

		List<Station> resultat = null;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			/*
			 * Pour récupérer tous les stations ref:
			 * https://stackoverflow.com/questions/24572092/using-java-generics-for-jpa-
			 * findall-query-with-where-clause
			 */

			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

			CriteriaQuery<Station> criteriaQuery = criteriaBuilder.createQuery(Station.class);

			Root<Station> root = criteriaQuery.from(Station.class);

			CriteriaQuery<Station> all = criteriaQuery.select(root);

			TypedQuery<Station> tousLesElements = manager.createQuery(all);

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
	public boolean create(Station station) {

		boolean resultat = false;

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			manager.persist(station);

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
	public boolean update(Station station) {

		boolean resultat = false;

		if (station == null || station.getIdStation() <= 0) {
			return resultat;
		}

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();

		Station ancienneStation = manager.find(Station.class, station.getIdStation());

		if (ancienneStation == null) {

			logger.info(Messages.MSG_TESANSACTION_EXECUTEE);

			return resultat;
		}

		try {

			transaction.begin();
			logger.info(Messages.MSG_TESANSACTION_COMMENCEE);

			ancienneStation.setNomStation(station.getNomStation());

			ancienneStation.setAdresse(station.getAdresse());

			ancienneStation.setVille(station.getVille());

			if (station.getHistoCarbs() != null && !station.getHistoCarbs().isEmpty()) {
				ancienneStation.setHistoCarbs(station.getHistoCarbs());
			}

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

			Station ancienneStation = manager.find(Station.class, id);

			manager.remove(ancienneStation);

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
	 * Cette opération n'est pas disponible.
	 */
	@Override
	public List<HistoCarb> findPrixStationDate(Station station, String Date) {
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		return null;
	}
	
	/**
	 * Cette opération n'est pas disponible.
	 */
	@Override
	public Utilisateur findByNom(String nom) {
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		return null;
	}

}
