package ma.ac.fstt.sim.jpa.dao;

import java.util.List;

import ma.ac.fstt.sim.jpa.entities.Carburant;
import ma.ac.fstt.sim.jpa.entities.HistoCarb;
import ma.ac.fstt.sim.jpa.entities.Station;
import ma.ac.fstt.sim.jpa.entities.Utilisateur;

public interface Dao <classe> {

	// CRUD
	
	boolean create(classe objet);

	classe find(int id);

	boolean update(classe objet);
	
	boolean delete(int id);

	List<classe> getAll();

	List<HistoCarb> findPrixStationDate(Station station, String Date);

	Utilisateur findByNom(String nom);

}
