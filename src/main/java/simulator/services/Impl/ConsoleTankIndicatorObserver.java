package simulator.services.Impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import simulator.services.TankIndicatorObserver;

@Service
@Qualifier("ConsoleTankIndicatorObserver")
public class ConsoleTankIndicatorObserver implements TankIndicatorObserver {

    @Override
    public void handleMessage(String name, double fuelInTank, double tankSize) {
        System.out.println(name + " " + fuelInTank + " " + tankSize);
    }
}
