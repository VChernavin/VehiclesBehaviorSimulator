package simulator.services;

import simulator.domain.vehicles.Vehicle;

public interface VehicleRegistry {
	void addIfNotFull(Vehicle vehicle);
	boolean isFull();
}
