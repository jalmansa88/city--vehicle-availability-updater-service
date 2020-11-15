package com.jalmansa.meepchallenge.repository;

import com.jalmansa.meepchallenge.domain.Vehicles;

public interface VehiclesRepository {
    public Vehicles loadCurrent();
    public void save(Vehicles vehicles);
}
