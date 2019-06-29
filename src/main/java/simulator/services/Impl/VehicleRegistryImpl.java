package simulator.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import simulator.domain.vehicles.Vehicle;
import simulator.services.VehicleRegistry;

@Service
public class VehicleRegistryImpl implements VehicleRegistry {
	private static final int maxSize = 1000;

	private volatile List<Vehicle> registry = new ArrayList<>();
	ExecutorService executorService = Executors.newFixedThreadPool(1000);

	@Override
	public synchronized void addIfNotFull(Vehicle vehicle) {
		if (!isFull()) {
			registry.add(vehicle);
			executorService.submit(vehicle);
		}
	}

	@Override
	public synchronized boolean isFull() {
		return registry.size() == maxSize;
	}
}
