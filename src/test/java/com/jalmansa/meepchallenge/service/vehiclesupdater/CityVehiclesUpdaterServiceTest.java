package com.jalmansa.meepchallenge.service.vehiclesupdater;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jalmansa.meepchallenge.domain.VehicleResource;
import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.domain.VehiclesDifference;
import com.jalmansa.meepchallenge.exception.UnexpectedApiResponseException;
import com.jalmansa.meepchallenge.repository.VehiclesRepository;
import com.jalmansa.meepchallenge.service.apiconsumer.ApiConsumer;
import com.jalmansa.meepchallenge.service.vehiclesupdater.impl.CityVehiclesUpdaterServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CityVehiclesUpdaterServiceTest {

    @Mock
    private ApiConsumer apiConsumer;

    @Mock
    private VehiclesRepository repo;

    private CityVehiclesUpdaterService service;

    @BeforeEach
    void setUp() {
        when(repo.loadCurrent()).thenReturn(buildOldVehicles());
        service = new CityVehiclesUpdaterServiceImpl(apiConsumer, repo);
    }

    @Test
    void shouldCheckDifferences() throws UnexpectedApiResponseException {
        Vehicles newVehicles = buildNewVehicles();

        when(apiConsumer.execute()).thenReturn(newVehicles);

        VehiclesDifference vehiclesDifference = service.execute();

        verify(apiConsumer, times(1)).execute();
        verify(repo, times(1)).save(newVehicles);
        assertEquals(1, vehiclesDifference.getNewAvailable().getVehicles().size());
        assertEquals(1, vehiclesDifference.getNoLongerAvailable().getVehicles().size());

        VehicleResource newResource = vehiclesDifference
            .getNewAvailable()
            .getVehicles()
            .stream().filter(v -> v.getId().equals("PT-LIS-A03"))
            .findFirst()
            .orElse(null);

        VehicleResource noLongerAvailablResource = vehiclesDifference
                .getNoLongerAvailable()
                .getVehicles()
                .stream().filter(v -> v.getId().equals("PT-LIS-A02"))
                .findFirst()
                .orElse(null);

        assertNotNull(newResource);
        assertNotNull(noLongerAvailablResource);
    }

    private Vehicles buildNewVehicles() {
        VehicleResource[] vehiclesArray = { buildVehicleResource("PT-LIS-A01", -9.14036, 38.734699), buildVehicleResource("PT-LIS-A03", 9.14036, 38.734699) };
        return Vehicles.of(vehiclesArray);
    }

    private Vehicles buildOldVehicles() {
        VehicleResource[] vehiclesArray = { buildVehicleResource("PT-LIS-A01", 9.14036, 40.50), buildVehicleResource("PT-LIS-A02", 9.14036, 38.734699) };
        return Vehicles.of(vehiclesArray);
    }

    private VehicleResource buildVehicleResource(String id, double x, double y) {
        return VehicleResource.builder()
                .id(id)
                .name("15ZC33")
                .x(x)
                .y(y)
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
