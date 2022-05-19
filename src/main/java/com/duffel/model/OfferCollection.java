package com.duffel.model;

import java.net.URISyntaxException;

public class OfferCollection extends PagedData<OfferResponse, OfferCollection> {

    static String getType() {
        return OfferResponse.class.getSimpleName();
    }

    public static OfferCollection get(String after) throws URISyntaxException {
        return get(OfferCollection.class, getType(), after);
    }

    public OfferCollection next() throws URISyntaxException {
        return next(OfferCollection.class, OfferResponse.class.getSimpleName());
    }


}
