package simulator.domain.vehicles;

import simulator.domain.IncidentObserver;
import simulator.domain.TankIndicatorObserved;

public interface Vehicle extends Runnable, TankIndicatorObserved, IncidentObserver {
}
