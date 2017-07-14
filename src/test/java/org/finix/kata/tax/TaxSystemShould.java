package org.finix.kata.tax;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;

import java.time.LocalDate;

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
    public void should_return_165GB_when_VANweight_less_than_3500kg(
            @InRange(minInt = 0, maxInt = 3499) int weight) {
        prepareVan(weight);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).weightInKg();
        assertThat(tax, is(165));
    }

    @Property
    public void should_return_190GBP_when_VANweight_greater_equals_3500kg(
            @InRange(minInt = 3500) int weight){
        prepareVan(weight);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).weightInKg();
        assertThat(tax, is(190));
    }


    @Property
    public void should_return_110GB_when_carRegister_before_20010301_and_engine_less_1550c(
            @InRange(min="01/01/1970", max = "01/03/2001", format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 0, maxInt = 1549) int engine){
        prepareCarWithEngine(register, engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).engineSize();

        assertThat(tax, is(110));
    }
    @Property
    public void should_return_165GB_when_carRegister_before_20010301_and_engine_greaterEquals_1550c(
            @InRange(min="01/01/1970", max = "01/03/2001", format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 1550) int engine){
        prepareCarWithEngine(register, engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).engineSize();

        assertThat(tax, is(165));
    }


    @Property
    public void should_return_65_when_carRegister_after_20010301_and_CO2Emission_under_101(
            @InRange(min = "02/03/2001", format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 0, maxInt = 100) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(65));
    }

    @Property
    public void should_return_75_when_carRegister_after_20010301_and_CO2Emission_between_101_120(
            @InRange(min = "02/03/2001", max = "31/12/2200" ,format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 101, maxInt = 120) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(75));
    }

    @Property
    public void should_return_105_when_carRegister_after_20010301_and_CO2Emission_between_121_150(
            @InRange(min = "02/03/2001", max = "31/12/2200" ,format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 121, maxInt = 150) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(105));
    }
    @Property
    public void should_return_125_when_carRegister_after_20010301_and_CO2Emission_between_151_165(
            @InRange(min = "02/03/2001", max = "31/12/2200" ,format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 151, maxInt = 165) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(125));
    }

    @Property
    public void should_return_145_when_carRegister_after_20010301_and_CO2Emission_between_166_185(
            @InRange(min = "02/03/2001", max = "31/12/2200" ,format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 166, maxInt = 185) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(145));
    }

    @Property
    public void should_return_160_when_carRegister_after_20010301_and_CO2Emission_over_185(
            @InRange(min = "02/03/2001", max = "31/12/2200" ,format = "dd/MM/yyyy") LocalDate register
            , @InRange(minInt = 186) int co2) {
        prepareCarWithCO2(register, co2);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).register();
        verify(vehicle).co2Emission();

        assertThat(tax, is(160));
    }

    @Property
    public void should_return_15_when_motoEngine_is_less_150(
            @InRange(minInt = 0, maxInt = 150) int engine){
        prepareMotorbike(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).engineSize();

        assertThat(tax, is(15));
    }

    @Property
    public void should_return_30_when_motoEngine_is_between_151_400(
            @InRange(minInt = 151, maxInt = 400) int engine){
        prepareMotorbike(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).engineSize();

        assertThat(tax, is(30));
    }
    @Property
    public void should_return_45_when_motoEngine_is_between_401_600(
            @InRange(minInt = 401, maxInt = 600) int engine){
        prepareMotorbike(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).engineSize();

        assertThat(tax, is(45));
    }
    @Property
    public void should_return_60_when_motoEngine_is_more_600(
            @InRange(minInt = 601) int engine){
        prepareMotorbike(engine);

        int tax = taxSystem.computeAnnualTax(vehicle);

        verify(vehicle).engineSize();

        assertThat(tax, is(60));
    }

    private void prepareMotorbike(@InRange(minInt = 601) int engine) {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.MOTORCYCLE);
        BDDMockito.given(vehicle.engineSize()).willReturn(engine);
    }

    private void prepareCarWithEngine(@InRange(min = "01/01/1970", max = "01/03/2001", format = "dd/MM/yyyy") LocalDate register, @InRange(minInt = 1550) int engine) {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(register);
        BDDMockito.given(vehicle.engineSize()).willReturn(engine);
    }

    private void prepareCarWithCO2(@InRange(min = "02/03/2001", max = "31/12/2200", format = "dd/MM/yyyy") LocalDate register, @InRange(minInt = 101, maxInt = 120) int co2) {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.CAR);
        BDDMockito.given(vehicle.register()).willReturn(register);
        BDDMockito.given(vehicle.co2Emission()).willReturn(co2);
    }

    private void prepareVan(@InRange(minInt = 3500) int weight) {
        BDDMockito.given(vehicle.type()).willReturn(VehicleType.VAN);
        BDDMockito.given(vehicle.weightInKg()).willReturn(weight);
    }


}
