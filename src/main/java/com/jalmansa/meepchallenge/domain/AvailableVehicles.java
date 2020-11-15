package com.jalmansa.meepchallenge.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Sets;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AvailableVehicles {

    private Set<VehicleResource> vehicles;

    public static AvailableVehicles of(VehicleResource[] vehiclesArray) {
        if (Objects.isNull(vehiclesArray)) {
            vehiclesArray = new VehicleResource[0];
        }
        return AvailableVehicles
                .builder()
                .vehicles(Sets.newHashSet(vehiclesArray))
                .build();
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(vehicles);
    }

    public Optional<VehicleResource> getVehicleById(String id) {
        return vehicles.stream()
            .filter(vehicle -> vehicle.getId().equals(id))
            .findFirst();
    }

    @Override
    public String toString() {
        return vehicles
                .stream()
                .map(VehicleResource::toString)
                .reduce("", String::concat);
    }
}
