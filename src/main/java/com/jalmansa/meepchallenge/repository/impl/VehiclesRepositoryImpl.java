package com.jalmansa.meepchallenge.repository.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.repository.VehiclesRepository;

import lombok.extern.slf4j.Slf4j;

/***
 * Helper class to load/save vehicles.
 * Only for test porpouse, should not be used in production!!
 *
 * @author javier
 *
 */
@Slf4j
@Service
public class VehiclesRepositoryImpl implements VehiclesRepository {

    @Override
    public Vehicles loadCurrent() {
        ObjectMapper mapper = new ObjectMapper();

        File resource;
        Vehicles output = null;
        try {
            resource = new ClassPathResource("vehicles.json").getFile();
            String vehiclesJson = new String(Files.readAllBytes(resource.toPath()));

            output = mapper.readValue(vehiclesJson, Vehicles.class);
        } catch (IOException e) {
            log.error("Error while reading from file");
        }

        return output;
    }

    @Override
    public void save(Vehicles vehicles) {

        ObjectMapper mapper = new ObjectMapper();
        FileWriter file = null;
        Resource resource = new ClassPathResource("vehicles.json");
        try {
            file = new FileWriter(resource.getFile().getAbsolutePath());
            String json = mapper.writeValueAsString(vehicles);

            file.write(json);
            file.flush();
        } catch (IOException e) {
            log.error("Error while writing objet to file");
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                log.error("Error while closing objet to file");
            }
        }

    }

}
