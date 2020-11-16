package com.jalmansa.meepchallenge.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {

    private Set<VehicleResource> availableVehicles;

    public static Vehicles of(VehicleResource[] vehiclesArray) {
        HashSet<VehicleResource> set = Optional.ofNullable(vehiclesArray)
            .map(Sets::newHashSet)
            .orElseGet(HashSet::new);

        return of(set);
    }

    public static Vehicles of(Set<VehicleResource> vehicles) {
        Set<VehicleResource> set = Optional.ofNullable(vehicles)
                .orElseGet(HashSet::new);

        return Vehicles
                .builder()
                .availableVehicles(set)
                .build();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(availableVehicles);
    }

    public Optional<VehicleResource> findVehicleById(String id) {
        return availableVehicles.stream()
            .filter(vehicle -> vehicle.getId().equals(id))
            .findFirst();
    }

    public boolean isAvailable(VehicleResource resource) {
        return availableVehicles.contains(resource);
    }

    @Override
    public String toString() {
        return availableVehicles
                .stream()
                .map(VehicleResource::toString)
                .collect(Collectors.joining(","));
    }
}
