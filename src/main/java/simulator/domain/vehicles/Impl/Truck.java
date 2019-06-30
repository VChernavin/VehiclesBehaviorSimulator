package simulator.domain.vehicles.Impl;

import simulator.services.VehicleRegistry;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Truck extends AbstractVehicle {
	private static final double consumption = 0.7;
	private  static double tankSize = 130d;
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

	public Truck(String name, VehicleRegistry vehicleRegistry) {
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
