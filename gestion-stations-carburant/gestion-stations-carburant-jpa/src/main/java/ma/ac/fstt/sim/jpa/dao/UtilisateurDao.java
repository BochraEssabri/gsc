package ma.ac.fstt.sim.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;
import ma.ac.fstt.sim.jpa.persistence.PersistenceManager;

public class UtilisateurDao implements Dao<Utilisateur> {

	private static final Logger logger = Log.getLogger(UtilisateurDao.class);

	/**
	 *  on récupère un manager pour commencer une transaction avec la BD.
	 *  On fait pas manager.close pour laisser Hibernate pour charger les dependances.
	 *  qu'on a déclarée Lazy quand on en aura besoin.
	 *  le close sera appelé à la fin de l'execution de l'application.
	 *  ref: https://stackoverflow.com/questions/11794633/trying-to-load-lazy-relationships-after-entitymanager-closes
	 *  
	 */
	private EntityManager manager = PersistenceManager.getInstance().getManager();
	
	public UtilisateurDao() {

	}
	
	@Override
	public Utilisateur findByNom(final String nom) {

		// la transaction sert à revenir en arrière en cas de problème.
		EntityTransaction transaction = manager.getTransaction();
		
		// Refs pour utilisation de where:
		/// https://www.objectdb.com/java/jpa/query/jpql/from
		/// https://stackoverflow.com/questions/14977018/jpa-how-to-get-entity-based-on-field-value-other-than-id
		try {
			transaction.begin();
		
			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
	
			CriteriaQuery<Utilisateur> criteriaQuery = criteriaBuilder.createQuery(Utilisateur.class);
			
			Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
			
			
			
			CriteriaQuery<Utilisateur> resultatQuery = criteriaQuery.select(root).where(
														criteriaBuilder.equal(root.get("nomUtilisateur"),nom)
														);
			
			TypedQuery<Utilisateur> resultatTypedQuery = manager.createQuery(resultatQuery);
			
			List<Utilisateur> resultat = resultatTypedQuery.getResultList();
			
			return resultat.size() > 0 ? resultat.get(0) : null; 
		}
		catch(Exception e) {
			logger.error(e);
		}
		finally {
			if(transaction.isActive()) {
				transaction.commit();
			}
			
		}
		return null;
	}
	
	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public Utilisateur find(final int id) {

		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		
		return null;
	}
	
	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public List<Utilisateur> getAll() {

		return null;

	}
	
	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public boolean create(Utilisateur utilisateur) {

		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		
		return false;
	}
	
	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public boolean update(Utilisateur utilisateur) {

		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		
		return false;
	}


	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public List<HistoCarb> findPrixStationDate(Station station, String Date) {
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		return null;
	}
	
	/**
	 * On a pas implémenté cette méthode pour l'instant.
	 * On a juste développé une méthode pour l'authentification via un utilisateur ajouté
	 * manuellement à la BD.
	 */
	@Override
	public boolean delete(int id) {
		
		logger.error(Messages.ERR_OPERATION_INDISPONIBLE);
		
		return false;
	}


}
