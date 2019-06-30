package simulator.domain.vehicles.Impl;

import simulator.services.VehicleRegistry;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Bus extends AbstractVehicle {
	private static final double consumption = 0.5;
	private static double tankSize = 100d;
	private static ReadWriteLock lock = new ReentrantReadWriteLock();

	private static void handleAlertEvent() {
		lock.writeLock().lock();
		try {
			double newCapacity = tankSize - (tankSize * 0.03);
			tankSize = newCapacity > 0 ? newCapacity : 0;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public Bus(String name, VehicleRegistry vehicleRegistry) {
		super(name, vehicleRegistry);
	}



	@Override
	protected double getTankSize() {
		lock.readLock().lock();
		try{
			return tankSize;
		}finally {
			lock.readLock().unlock();
		}
	}

	@Override
	protected double getConsumption() {
		return consumption;
	}

	@Override
	protected void generateAlertEvent() {
		handleAlertEvent();
	}
}
