package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferRequest extends PostData<OfferRequest, OfferCollection> {

    static String getType() {
        return "offer_requests";
    }

    public static OfferCollection post(OfferRequest request) throws URISyntaxException {
        OfferRequest request2 = new OfferRequest();
        request2.setData(request);
        return post(request2, getType(), OfferCollection.class);
    }

    @JsonProperty("max_connections")
    public int maxConnections;

    @JsonProperty("cabin_class")
    public String cabinClass;

    @JsonProperty("slices")
    public List<Slice> slices;

    @JsonProperty("passengers")
    public List<Passenger> passengers;

    public static class Slice {
        @JsonProperty("origin")
        public String origin;

        @JsonProperty("destination")
        public String destination;

        @JsonProperty("departure_date")
        public String departureDate;
    }

    public static class Passenger {
        @JsonProperty("family_name")
        public String familyName;

        @JsonProperty("given_name")
        public String givenName;

        @JsonProperty("type")
        public String type;

        @JsonProperty("age")
        public Integer age;
    }
}
