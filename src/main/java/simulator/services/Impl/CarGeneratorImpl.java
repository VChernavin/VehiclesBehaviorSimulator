package simulator.services.Impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simulator.domain.vehicles.Impl.Car;
import simulator.services.CarGenerator;
import simulator.services.VehicleRegistry;

@Service
public class CarGeneratorImpl implements CarGenerator {

	private VehicleRegistry vehicleRegistry;

	@Autowired
	public CarGeneratorImpl(VehicleRegistry vehicleRegistry) {
		this.vehicleRegistry = vehicleRegistry;
	}

	@PostConstruct
	private void postConstruct() {
		 new Thread(() -> {
			int i = 0;
			while (!vehicleRegistry.isFull()) {
				Car car = new Car("Car " + i, vehicleRegistry);
				vehicleRegistry.addIfNotFull(car);
				i++;
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
