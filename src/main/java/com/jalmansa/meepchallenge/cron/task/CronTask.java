package com.jalmansa.meepchallenge.cron.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jalmansa.meepchallenge.service.vehiclesupdater.CityVehiclesUpdaterService;

@Component
public class CronTask {

    @Autowired
    private CityVehiclesUpdaterService service;

    @Scheduled(cron = "${task.cron.expresion}")
    public void task() {
        service.execute();
    }
}
