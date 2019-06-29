package simulator.services.Impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simulator.domain.vehicles.Impl.Truck;
import simulator.services.TruckGenerator;
import simulator.services.VehicleRegistry;

@Service
public class TruckGeneratorImpl implements TruckGenerator {
	private VehicleRegistry vehicleRegistry;

	@Autowired
	public TruckGeneratorImpl(VehicleRegistry vehicleRegistry) {
		this.vehicleRegistry = vehicleRegistry;
	}

	@PostConstruct
	private void postConstruct() {
		new Thread(() -> {
			int i = 0;
			while (!vehicleRegistry.isFull()) {
				Truck truck = new Truck("Truck " + i);
				truck.addObserver(System.out::println);
				vehicleRegistry.addIfNotFull(truck);
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
