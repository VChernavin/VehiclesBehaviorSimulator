package simulator.domain.vehicles.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import simulator.services.TankIndicatorObserver;
import simulator.domain.vehicles.Vehicle;

public abstract class AbstractVehicle implements Vehicle {

	private double tank = getMaxTankCapacity();

	private List<TankIndicatorObserver> tankIndicatorObservers = new ArrayList<>();
	private final String name;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Random rand = new Random();

	public AbstractVehicle(String name) {
		this.name = name;
	}

	@Override
	public void run() {

		try {
			while (true) {
				boolean isTankAlmostEmpty;
				lock.readLock().lock();
				try {
					isTankAlmostEmpty = tank > getMaxTankCapacity() * getConsumption();
				}
				finally {
					lock.readLock().unlock();
				}
				if (isTankAlmostEmpty) {
					Thread.sleep(5000);
					useFuel();
				} else {
					Thread.sleep(10000);
					fillTank();
				}

				notifyObservers();

				if (rand.nextInt(10) == 1) {
					generateAlertEvent();
				}

			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void handleIncidentEvent() {
		lock.writeLock().lock();
		try {
			tank = 0;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	public String getName() {
		return name;
	}

	public double getTank() {
		return tank;
	}

	@Override
	public void addObserver(TankIndicatorObserver tankIndicatorObserver) {
		tankIndicatorObservers.add(tankIndicatorObserver);
	}

	@Override
	public void removeObserver(TankIndicatorObserver tankIndicatorObserver) {
		tankIndicatorObservers.remove(tankIndicatorObserver);
	}

	@Override
	public void notifyObservers() {
		lock.readLock().lock();
		try {
			tankIndicatorObservers.forEach(o -> o.handleMessage(name + " tank = " + tank));
		}
		finally {
			lock.readLock().unlock();
		}
	}

	private void useFuel() {
		lock.writeLock().lock();
		try {
			tank -= 5;
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	private void fillTank() {
		lock.writeLock().lock();
		try {
			tank = getMaxTankCapacity();
		}
		finally {
			lock.writeLock().unlock();
		}
	}

	abstract protected double getMaxTankCapacity();

	abstract protected double getConsumption();

	abstract protected void generateAlertEvent();
}
