package org.finix.kata.tax;

import java.time.LocalDate;

/**
 * Created by alex on 7/14/17.
 */
public class TaxSystem {
    public int computeAnnualTax(IVehicle vehicle) {
        switch (vehicle.type()){
            case VAN:
                return computeVanTax(vehicle);
            case CAR:
                if(vehicle.register().isBefore(LocalDate.of(2001, 3, 1))){
                    if(vehicle.engineSize() < 1550){
                        return 110;
                    }else {
                        return 165;
                    }
                }
        }
        throw new UnsupportedOperationException();
    }

    private int computeVanTax(IVehicle vehicle) {
        if(vehicle.weight() < 3500){
            return 165;
        }
        else {
            return 190;
        }
    }
}
