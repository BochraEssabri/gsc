package ma.ac.fstt.sim.jpa.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ma.ac.fstt.sim.jpa.dao.CarburantDao;

/**
 * Pour initialiser l'EntityManager de JPA pour toutes les entités.
 * On en a besoin d'une seule instance de cette classe, on va utiliser donc le design pattern singleton
 *
 */
public class PersistenceManager {

	private static final Logger logger = LogManager.getLogger(PersistenceManager.class);
	
	private static final PersistenceManager INSTANCE = new PersistenceManager();
	/**
	 * C'est  <persistence-unit name="jpaGsc" > dans le fichier de configuration persistence.xml
	 * Une constante en java est en majuscule.
	 */
	private final String UNITE_PERSISTENCE = "jpaGsc";
	
	/**
	 * Entité manager est à créer une seule fois, donc final.
	 */
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(UNITE_PERSISTENCE);
	
    /**
     * Pour pouvoir implementer le design pattern singleton. 
     * constructeur privé.
     */
    private PersistenceManager() {
    	logger.info("EntityManager intialisé");
    }
    
    public static PersistenceManager getInstance() {
    	return INSTANCE;
    }
    
    /**
     * Prépare et retourne une transaction pour controler la communication avec la BD.
     * @return 
     * @return
     */
    public  EntityManager getManager() {
    	return ENTITY_MANAGER_FACTORY.createEntityManager();
    	
    }
    
}
