package com.jalmansa.meepchallenge.service.apiconsumer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.jalmansa.meepchallenge.cron.task.CronTask;
import com.jalmansa.meepchallenge.domain.VehicleResource;
import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.service.apiconsumer.impl.ApiConsumerImpl;

@ActiveProfiles("test")
@MockBean(CronTask.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiConsumerIntegrationTest {

    @Autowired
    ApiProperties apiProperties;

    @Autowired
    UrlValidator urlValidator;

    @Mock
    private RestTemplate restTemplate;

    private ApiConsumer apiConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiConsumer = new ApiConsumerImpl(apiProperties, urlValidator, restTemplate);
    }

    @Test
    void shouldPerfomApiCallAndReturnAValue() {
        VehicleResource[] dataResponse = { buildVehicleResource() };
        when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(VehicleResource[].class)))
                .thenReturn(ResponseEntity.ok(dataResponse));

        Vehicles vehicles = apiConsumer.execute();

        Mockito.verify(restTemplate, times(1)).getForEntity(
                Mockito.argThat((String s) -> List.of(
                        "apidev.meep.me",
                        "lisboa",
                        "upperRightLatLon=38.739429,-9.137115",
                        "companyZoneIds=545,467,473",
                        "lowerLeftLatLon=38.711046,-9.160096").stream().allMatch(s::contains)),
                Mockito.eq(VehicleResource[].class));
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
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
