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
import static org.mockito.Mockito.verify;


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
        BDDMockito.given(vehicle.weightInKg()).willReturn(100);

        int taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(165));

        BDDMockito.given(vehicle.weightInKg()).willReturn(4000);
        taxResult = tax.computeAnnualTax(vehicle);

        assertThat(taxResult, is(190));
    }

    @Test
    public void should_check_on_cc_if_car_register_before_01March2001() {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(LocalDate.of(2001,2,14));
        BDDMockito.given(vehicle.engineSize()).willReturn(1400);

        int taxResult = tax.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).engineSize();
    }
    @Test
    public void should_check_on_c02_if_car_register_after_01March2001() {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(LocalDate.of(2001,3,2));
        BDDMockito.given(vehicle.co2Emission()).willReturn(165);

        int taxResult = tax.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();
    }
    
    @Test 
        public void should_check_on_engine_size_if_motorcycle() {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.MOTORCYCLE);
        BDDMockito.given(vehicle.engineSize()).willReturn(155);

        int taxResult = tax.computeAnnualTax(vehicle);

        verify(vehicle).engineSize();
    }
}
