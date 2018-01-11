package com.tsoft.app.domain.enumeration;

/**
 * Created by tchipnangngansopa on 07/07/2017.
 */

public enum RecommandationVisitDoctor {

    URGENT("Urgent visit of the MD"),CONFIRMATION("Visit of MD for confirmation"),BP_TBP("BP TBD control every year ");

    private String name="";

    RecommandationVisitDoctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
