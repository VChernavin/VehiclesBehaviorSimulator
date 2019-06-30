package vehicles.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vehicles.backend.dto.VehicleDto;
import vehicles.backend.services.VehicleSevice;

@RestController
@RequestMapping("/api")
public class VehicleController {
	private final VehicleSevice vehicleSevice;

	@Autowired
	public VehicleController(VehicleSevice vehicleSevice) {
		this.vehicleSevice = vehicleSevice;
	}

	@PostMapping
	public void saveOrUpdate(@RequestBody VehicleDto vehicle) {
		vehicleSevice.saveOrUpdate(vehicle);
	}


}
