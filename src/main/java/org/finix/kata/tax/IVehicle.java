package org.finix.kata.tax;

import java.time.LocalDate;

/**
 * Created by alex on 7/14/17.
 */
public interface IVehicle {
    VehicleType type();

    int weightInKg();

    LocalDate register();

    int engineSize();

    int co2Emission();
}
