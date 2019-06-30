package vehicles.simulator.domain;

import vehicles.simulator.services.TankIndicatorObserver;

public interface TankIndicatorObserved {
	void addObserver(TankIndicatorObserver tankIndicatorObserver);
	void removeObserver(TankIndicatorObserver tankIndicatorObserver);
	void notifyObservers();
}
