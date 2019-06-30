package vehicles.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "VEHICLES")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1497842219188451244L;

	@Id
	@Column(name = "PR_KEY", unique = true)
	private String name;
	@Column
	private double fuelInTank;
	@Column
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
