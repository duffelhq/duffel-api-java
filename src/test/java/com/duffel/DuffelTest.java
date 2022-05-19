
package com.duffel;

import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import com.duffel.model.OfferRequest;
import com.duffel.model.OfferResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.client.MockServerClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockServerExtension.class)
class DuffelTest {
    private static final Logger LOG = LogManager.getLogger(DuffelTest.class);

    @Test void someLibraryMethodReturnsTrue(MockServerClient client) throws URISyntaxException {

        Duffel.apiKey = "duffel_test_opbVnrL-pihnqp-SkYssn2xtPhVsjHzn21SA8uGzPAy";
        AircraftCollection air = Aircraft.get();
        while (air.hasNext()) {
            LOG.info(air.getData());
            air = air.next();
        }


        LOG.info(Aircraft.getById("arc_00009oBdrPis4D1mAnkllK"));

    }

    @Test void postTest() throws URISyntaxException {

        Duffel.apiKey = "duffel_test_opbVnrL-pihnqp-SkYssn2xtPhVsjHzn21SA8uGzPAy";

        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.departureDate = "2022-05-20";
        slice.origin = "LHR";
        slice.destination = "STR";
        List<OfferRequest.Slice> slices = new ArrayList<>();
        slices.add(slice);

        OfferRequest.Passenger passenger = new OfferRequest.Passenger();
        passenger.type = "adult";
        passenger.givenName = "Test";
        passenger.familyName = "Test";
        List<OfferRequest.Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        OfferRequest request = new OfferRequest();
        request.maxConnections = 0;
        request.cabinClass = "economy";
        request.slices = slices;
        request.passengers = passengers;

        LOG.info(OfferRequest.post(request));
        Offer offer = OfferResponse.offers(0)
                Offer.get("123")

    }

}
