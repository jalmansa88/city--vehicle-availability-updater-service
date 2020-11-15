package com.jalmansa.meepchallenge.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VehiclesTest {

    @Test
    void shouldInstanciateWithNoVehicles() {
        Vehicles vehicles = Vehicles.of(null);
        assertNotNull(vehicles);
        assertTrue(vehicles.isEmpty());
    }

    @Test
    void shouldInstanciateAndFindResourceById() {
        VehicleResource[] dataResponse = { buildVehicleResource() };
        Vehicles vehicles = Vehicles.of(dataResponse);
        assertNotNull(vehicles.findVehicleById("PT-LIS-A00404"));
    }

    private VehicleResource buildVehicleResource() {
        return VehicleResource.builder()
                .id("PT-LIS-A00404")
                .name("15ZC33")
                .x(-9.14036)
                .y(38.734699)
                .licencePlate("15ZC33")
                .range(66)
                .batteryLevel(88)
                .helmets(2)
                .model("Askoll")
                .resourceImageId("vehicle_gen_ecooltra")
                .realTimeData(true)
                .resourceType("MOPED")
                .companyZoneId(473)
                .build();
    }

}
