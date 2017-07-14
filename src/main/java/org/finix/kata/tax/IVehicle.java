package org.finix.kata.tax;

import java.time.LocalDate;

/**
 * Created by alex on 7/14/17.
 */
public interface IVehicle {
    VehicleType type();

    int weight();

    LocalDate register();

    int engineSize();
}
