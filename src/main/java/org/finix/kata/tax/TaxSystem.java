package org.finix.kata.tax;

import java.time.LocalDate;

/**
 * Created by alex on 7/14/17.
 */
public class TaxSystem {
    public int computeAnnualTax(IVehicle vehicle) {
        VehicleType type = vehicle.type();
        if (type == VehicleType.VAN) {
            return computeVanTax(vehicle);
        } else if (type == VehicleType.CAR) {
            return computeCarTax(vehicle);
        } else  if(type == VehicleType.MOTORCYCLE) {
            return computeMotoTax(vehicle);
        }
        throw new UnsupportedOperationException();
    }

    private int computeMotoTax(IVehicle vehicle) {
        int engineSize = vehicle.engineSize();
        if(engineSize <= 150){
            return 15;
        }else if(engineSize <= 400){
            return 30;
        }else if(engineSize <= 600){
            return 45;
        }else {
            return 60;
        }
    }

    private int computeCarTax(IVehicle vehicle) {
        if (vehicle.register().isBefore(LocalDate.of(2001, 3, 1))) {
            if (vehicle.engineSize() < 1550) {
                return 110;
            } else {
                return 165;
            }
        }else {
            int co2 = vehicle.co2Emission();
            if(co2 <= 100){
                return 65;
            }else if(co2 <= 120){
                return 75;
            }else if(co2 <= 150){
                return 105;
            }else if(co2 <= 165){
                return 125;
            }else  if(co2 <= 185){
                return 145;
            }else {
                return 160;
            }
        }
    }

    private int computeVanTax(IVehicle vehicle) {
        if(vehicle.weightInKg() < 3500){
            return 165;
        }
        else {
            return 190;
        }
    }
}
