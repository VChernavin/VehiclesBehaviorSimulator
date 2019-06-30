package vehicles.backend.dto;

public class VehicleDto {

	private String name;
	private double fuelInTank;
	private double tankSize;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFuelInTank() {
		return fuelInTank;
	}

	public void setFuelInTank(double fuelInTank) {
		this.fuelInTank = fuelInTank;
	}

	public double getTankSize() {
		return tankSize;
	}

	public void setTankSize(double tankSize) {
		this.tankSize = tankSize;
	}
}
