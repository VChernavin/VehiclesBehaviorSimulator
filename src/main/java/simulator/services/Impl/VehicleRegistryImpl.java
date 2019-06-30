package simulator.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simulator.domain.vehicles.Vehicle;
import simulator.services.TankIndicatorObserver;
import simulator.services.VehicleRegistry;

@Service
public class VehicleRegistryImpl implements VehicleRegistry {
	private static final int maxSize = 1000;

	private ExecutorService executorService = Executors.newFixedThreadPool(1000);
	private Random rand = new Random();
	private TankIndicatorObserver indicatorObserver;

	private volatile List<Vehicle> registry = new ArrayList<>();

	@Autowired
	public VehicleRegistryImpl(TankIndicatorObserver indicatorObserver) {
		this.indicatorObserver = indicatorObserver;
	}

	@Override
	public synchronized void addIfNotFull(Vehicle vehicle) {
		if (!isFull()) {
			registry.add(vehicle);
			vehicle.addObserver(indicatorObserver);
			executorService.submit(vehicle);
		}
	}

	@Override
	public synchronized boolean isFull() {
		return registry.size() == maxSize;
	}

	@Override
	public Vehicle getRandomVehicle() {
		if (!registry.isEmpty()) {
			return registry.get(rand.nextInt(registry.size()-1));

		}
		return null;
	}
}
