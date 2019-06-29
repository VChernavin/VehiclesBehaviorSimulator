package simulator.domain;

import simulator.services.TankIndicatorObserver;

public interface TankIndicatorObserved {
	void addObserver(TankIndicatorObserver tankIndicatorObserver);
	void removeObserver(TankIndicatorObserver tankIndicatorObserver);
	void notifyObservers();
}
