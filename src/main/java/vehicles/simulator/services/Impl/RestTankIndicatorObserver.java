package vehicles.simulator.services.Impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vehicles.backend.dto.VehicleDto;
import vehicles.simulator.services.TankIndicatorObserver;

@Service
@Qualifier("RestTankIndicatorObserver")
public class RestTankIndicatorObserver implements TankIndicatorObserver {
	private final RestTemplate restTemplate = new RestTemplate();
	private URI uri;

	{
		try {
			uri = new URI("http://localhost:8080/api");
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleMessage(String name, double fuelInTank, double tankSize) {
		VehicleDto vehicleDto = new VehicleDto();
		vehicleDto.setName(name);
		vehicleDto.setFuelInTank(fuelInTank);
		vehicleDto.setTankSize(tankSize);
		restTemplate.postForEntity(uri, vehicleDto, String.class);
	}


}
