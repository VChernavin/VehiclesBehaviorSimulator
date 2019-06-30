package simulator.services;

public interface TankIndicatorObserver {
	void handleMessage(String name,double fuelInTank,double tankSize);
}
