package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.Aircraft;
import com.duffel.model.Airline;
import com.duffel.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExploringDataIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void exploringData() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");

        DuffelApiClient client = new DuffelApiClient(testApiKey);
        LOG.info("🚀 Created a Duffel client");

        List<Location> airports = client.airportService.getAll();
        LOG.info("🛄 Fetched {} airports", airports.size());
        Location airport = airports.get(0);
        LOG.info("🗺 Airport {} ({}) is located at {} {}",
                airport.getName(), airport.getIataCode(), airport.getLatitude(), airport.getLongitude());

        List<Aircraft> aircraft = client.aircraftService.getAll();
        LOG.info("🛩 Fetched {} aircraft", aircraft.size());
        Aircraft selectedAircraft = aircraft.get(11);
        LOG.info("✈️ Aircraft {} has IATA code {}", selectedAircraft.getName(), selectedAircraft.getIataCode());

        List<Airline> airlines = client.airlineService.getAll();
        LOG.info("🇪🇺 Fetched {} airlines", airlines.size());
        Airline airline = airlines.get(204);
        LOG.info("🎫 Airline {} has IATA code {}", airline.getName(), airline.getIataCode());
    }

}
