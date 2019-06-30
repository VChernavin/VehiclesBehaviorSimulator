package vehicles.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vehicles.backend.dao.VehicleDao;
import vehicles.backend.dto.VehicleDto;
import vehicles.backend.models.Vehicle;
import vehicles.backend.services.VehicleSevice;

@Service
public class VehicleServiceImpl implements VehicleSevice {
	private final VehicleDao vehicleDao;

	@Autowired
	public VehicleServiceImpl(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	@Override
	public void saveOrUpdate(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setName(vehicleDto.getName());
		vehicle.setFuelInTank(vehicleDto.getFuelInTank());
		vehicle.setTankSize(vehicleDto.getTankSize());
		vehicleDao.saveOrUpdate(vehicle);
	}
}
