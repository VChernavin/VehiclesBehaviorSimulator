package simulator.services.Impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simulator.domain.vehicles.Impl.Bus;
import simulator.services.BusGenerator;
import simulator.services.VehicleRegistry;

@Service
public class BusGeneratorImpl implements BusGenerator {
	private VehicleRegistry vehicleRegistry;

	@Autowired
	public BusGeneratorImpl(VehicleRegistry vehicleRegistry) {
		this.vehicleRegistry = vehicleRegistry;
	}

	@PostConstruct
	private void postConstruct() {
		new Thread(() -> {
			int i = 0;
			while (!vehicleRegistry.isFull()) {
				Bus car = new Bus("Bus " + i, vehicleRegistry);
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
