package com.duffel.service;

import com.duffel.Duffel;
import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import com.duffel.net.HttpClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;

public class AircraftService extends HttpClient {


    public static AircraftCollection get() throws URISyntaxException {
        new HttpClient(new URI(Duffel.API_ENDPOINT + "/" + clazz.getSimpleName().toLowerCase(Locale.ROOT))).get(collectionClazz);
        return get(Aircraft.class, AircraftCollection.class);
    }

    public AircraftService(URI uri) {
        super(uri);
    }

    public AircraftService(URI uri, Map<String, String> headers) {
        super(uri, headers);
    }


}
