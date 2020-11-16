package com.jalmansa.meepchallenge.service.vehiclesupdater;

import com.jalmansa.meepchallenge.domain.VehiclesDifference;
import com.jalmansa.meepchallenge.exception.UnexpectedApiResponseException;

public interface CityVehiclesUpdaterService {
    public VehiclesDifference execute() throws UnexpectedApiResponseException;
}
