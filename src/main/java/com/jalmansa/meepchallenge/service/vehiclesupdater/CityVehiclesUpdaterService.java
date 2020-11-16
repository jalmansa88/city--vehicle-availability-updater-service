package com.jalmansa.meepchallenge.service.vehiclesupdater;

import com.jalmansa.meepchallenge.exception.UnexpectedApiResponseException;

public interface CityVehiclesUpdaterService {
    public void execute() throws UnexpectedApiResponseException;
}
