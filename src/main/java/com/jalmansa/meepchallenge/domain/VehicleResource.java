package com.jalmansa.meepchallenge.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VehicleResource {
    private String id;
    private String name;
    private double x;
    private double y;
    private String licencePlate;
    private int range;
    private int batteryLevel;
    private int helmets;
    private String model;
    private String resourceImageId;
    private boolean realTimeData;
    private String resourceType;
    private int companyZoneId;

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        return id.equals(((VehicleResource) obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
