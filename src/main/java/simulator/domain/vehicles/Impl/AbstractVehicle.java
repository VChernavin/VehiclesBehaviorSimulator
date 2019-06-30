package simulator.domain.vehicles.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import simulator.services.TankIndicatorObserver;
import simulator.domain.vehicles.Vehicle;
import simulator.services.VehicleRegistry;

public abstract class AbstractVehicle implements Vehicle {
    private final String name;
    private final VehicleRegistry vehicleRegistry;
    private double fuelInTank = getTankSize();

    private List<TankIndicatorObserver> tankIndicatorObservers = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Random rand = new Random();


    public AbstractVehicle(String name, VehicleRegistry vehicleRegistry) {
        this.name = name;
        this.vehicleRegistry = vehicleRegistry;
    }

    @Override
    public void run() {

        try {
            while (true) {
                boolean isTankAlmostEmpty;
                generateIncident();
                lock.readLock().lock();
                try {
                    isTankAlmostEmpty = fuelInTank > getTankSize() * getConsumption();
                } finally {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleIncidentEvent() {
        lock.writeLock().lock();
        try {
            fuelInTank = 0;
        } finally {
            lock.writeLock().unlock();
        }
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
            tankIndicatorObservers.forEach(o -> o.handleMessage(name, fuelInTank, getTankSize()));
        } finally {
            lock.readLock().unlock();
        }
    }

    private void useFuel() {
        lock.writeLock().lock();
        try {
            fuelInTank -= 5;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void fillTank() {
        lock.writeLock().lock();
        try {
            fuelInTank = getTankSize();
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void generateIncident() {
        if (rand.nextInt(10) == 1) {
            Vehicle randomVehicle = vehicleRegistry.getRandomVehicle();
            if (randomVehicle != null && randomVehicle != this) {
                randomVehicle.handleIncidentEvent();
                lock.writeLock().lock();
                try {
                    fuelInTank = 0;
                } finally {
                    lock.writeLock().unlock();
                }
            }

        }
    }

    abstract protected double getTankSize();

    abstract protected double getConsumption();

    abstract protected void generateAlertEvent();
}
