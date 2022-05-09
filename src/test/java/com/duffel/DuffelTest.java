
package com.duffel;

import com.duffel.model.Aircraft;
import com.duffel.net.HttpClient;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

class DuffelTest {
    @Test void someLibraryMethodReturnsTrue() throws URISyntaxException {
        Duffel.apiKey = "";
        HttpClient client = new HttpClient(new URI(Duffel.API_ENDPOINT + "/air/aircraft"));
        client.get(Aircraft.class);
    }
}
