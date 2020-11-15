package com.jalmansa.meepchallenge.service.vehiclesupdater.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jalmansa.meepchallenge.service.apiconsumer.ApiConsumer;
import com.jalmansa.meepchallenge.service.vehiclesupdater.UpdateCityVehiclesService;

@Service
public class UpdateCityVehiclesServiceImpl implements UpdateCityVehiclesService {

    private ApiConsumer apiConsumer;

    @Autowired
    public UpdateCityVehiclesServiceImpl(ApiConsumer apiConsumer) {
        this.apiConsumer = apiConsumer;
    }

    @Override
    public void execute() {
        apiConsumer.execute();
    }

}
