package vehicles.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import vehicles.backend.models.Vehicle;

@Repository
@Transactional
public class VehicleDao {

	@PersistenceContext
	private EntityManager entityManager;


	public void saveOrUpdate(Vehicle vehicle) {
		if(entityManager.find(Vehicle.class, vehicle.getName()) != null) {
			entityManager.merge(vehicle);
		} else {
			entityManager.persist(vehicle);
		}
	}

	public void update(Vehicle vehicle) {
		entityManager.merge(vehicle);
	}
}
