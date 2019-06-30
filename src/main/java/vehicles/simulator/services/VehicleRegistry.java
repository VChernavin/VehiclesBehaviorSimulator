package vehicles.simulator.services;

import vehicles.simulator.domain.vehicles.Vehicle;

public interface VehicleRegistry {
	void addIfNotFull(Vehicle vehicle);
	boolean isFull();
	Vehicle getRandomVehicle();
}
