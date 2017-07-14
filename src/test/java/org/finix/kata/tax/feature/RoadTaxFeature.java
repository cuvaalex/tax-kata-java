package org.finix.kata.tax.feature;

import org.finix.kata.tax.IVehicle;
import org.finix.kata.tax.TaxSystem;
import org.finix.kata.tax.VehicleType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by alex on 7/14/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoadTaxFeature {

    @Mock
    IVehicle vehicle;
    private TaxSystem tax;

    @Before
    public void init() {
        tax = new TaxSystem();
    }

    @Test
    public void should_return_165GBP_when_less_3500kg_otherwise_190GBP() {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.VAN);
        BDDMockito.given(vehicle.weight()).willReturn(100);

        int taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(165));

        BDDMockito.given(vehicle.weight()).willReturn(4000);
        taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(190));
    }

    @Test
    public void should_check_on_cc_if_car_register_before_01March2001() {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(LocalDate.of(2001,2,14));
        BDDMockito.given(vehicle.engineSize()).willReturn(1400);

        int taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(110));

        BDDMockito.given(vehicle.engineSize()).willReturn(1700);

        taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(165));
    }
}
