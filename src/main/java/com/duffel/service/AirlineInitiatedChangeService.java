package com.duffel.service;

import com.duffel.model.AirlineInitiatedChange;
import com.duffel.model.AirlineInitiatedChangeCollection;
import com.duffel.model.AirlineInitiatedChangeUpdate;
import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

public class AirlineInitiatedChangeService extends PostResource<AirlineInitiatedChange, AirlineInitiatedChangeCollection> {

    private static final String ENDPOINT = "/air/airline_initiated_changes";

    public AirlineInitiatedChangeService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public AirlineInitiatedChangeCollection list(String orderId) {
        return super.get(AirlineInitiatedChangeCollection.class, "?order_id=" + orderId);
    }

    public AirlineInitiatedChange accept(String aicId) {
        return super.post(AirlineInitiatedChange.class, aicId + "/actions/accept", null).getData();
    }

    public AirlineInitiatedChange update(String aicId, AirlineInitiatedChangeUpdate update) {
        return super.patch(AirlineInitiatedChange.class, aicId, new PostData<>(update)).getData();
    }

}
