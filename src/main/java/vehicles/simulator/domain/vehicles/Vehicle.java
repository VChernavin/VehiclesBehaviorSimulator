package vehicles.simulator.domain.vehicles;

import vehicles.simulator.domain.IncidentObserver;
import vehicles.simulator.domain.TankIndicatorObserved;

public interface Vehicle extends Runnable, TankIndicatorObserved, IncidentObserver {
}
