package com.jalmansa.meepchallenge.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehiclesDifference {
    private Vehicles noLongerAvailable;
    private Vehicles newAvailable;

}
