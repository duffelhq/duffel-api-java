package com.duffel.model;

import java.net.URISyntaxException;

public class AircraftCollection extends PagedData<Aircraft, AircraftCollection> {

    static String getType() {
        return Aircraft.class.getSimpleName();
    }

    public static AircraftCollection get(String after) throws URISyntaxException {
        return get(AircraftCollection.class, getType(), after);
    }

    public AircraftCollection next() throws URISyntaxException {
        return next(AircraftCollection.class, Aircraft.class.getSimpleName());
    }

}
