package com.jalmansa.meepchallenge.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jalmansa.meepchallenge.domain.VehicleResource;
import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.repository.impl.VehiclesRepositoryImpl;

class VehiclesRepositoryTest {

    private VehiclesRepository repo;

    @BeforeEach
    void setUp() {
        repo = new VehiclesRepositoryImpl();
    }

    @Test
    void shouldReadFromFile() {
        Vehicles current = repo.loadCurrent();
        assertNotNull(current);
    }

    @Test
    void shouldWriteFile() {
        VehicleResource[] dataResponse = { buildVehicleResource() };
        Vehicles vehicles = Vehicles.of(dataResponse);

        repo.save(vehicles);
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
