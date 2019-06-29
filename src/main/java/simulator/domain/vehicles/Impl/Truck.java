package simulator.domain.vehicles.Impl;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Truck extends AbstractVehicle {
	private static final double consumption = 0.7;
	private  static double maxTankCapacity = 130d;
	private static ReadWriteLock lock = new ReentrantReadWriteLock();

	private static void handleAlertEvent() {
		lock.writeLock().lock();
		try {
			double newCapacity = maxTankCapacity - (maxTankCapacity * 0.03);
			maxTankCapacity = newCapacity > 0 ? newCapacity : 0;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public Truck(String name) {
		super(name);
	}

	@Override
	protected double getMaxTankCapacity() {
		lock.readLock().lock();
		try{
			return maxTankCapacity;
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
