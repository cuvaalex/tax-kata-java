package org.finix.kata.tax;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 7/14/17.
 */
@RunWith(JUnitQuickcheck.class)
public class TaxSystemShould {
    private TaxSystem taxSystem;
    private IVehicle vehicle = mock(IVehicle.class);

    @Before
    public void init() {
        taxSystem = new TaxSystem();
    }

    @Property
    public void should_return_165GB_when_VANweight_less_than_3500kg(@InRange(minInt = 0, maxInt = 3500) int weight) {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.VAN);
        BDDMockito.given(vehicle.weight()).willReturn(weight);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).weight();
        assertThat(tax, is(165));
    }

    @Property
    public void should_return_190GBP_when_VANweight_greater_equals_3500kg(@InRange(minInt = 3500) int weight){
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.VAN);
        BDDMockito.given(vehicle.weight()).willReturn(weight);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).weight();
        assertThat(tax, is(190));
    }

    @Property
    public void should_return_110GB_when_carRegister_before_20010301_and_engine_less_1550c(
            @InRange(min="01/01/1970", max = "01/03/2001", format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 0, maxInt = 1549) int engine){
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(register);
        BDDMockito.given(vehicle.engineSize()).willReturn(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).engineSize();

        assertThat(tax, is(110));
    }
    @Property
    public void should_return_165GB_when_carRegister_before_20010301_and_engine_greaterEquals_1550c(
            @InRange(min="01/01/1970", max = "01/03/2001", format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 1550) int engine){
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(register);
        BDDMockito.given(vehicle.engineSize()).willReturn(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).engineSize();

        assertThat(tax, is(165));
    }

}
