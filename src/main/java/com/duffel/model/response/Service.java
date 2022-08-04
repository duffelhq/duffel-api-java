package com.duffel.model.response;

import com.duffel.model.response.order.metadata.BaggageMetadata;
import com.duffel.model.response.order.metadata.Metadata;
import com.duffel.model.response.order.metadata.SeatMetadata;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class Service {

    ///  <summary>
    ///  Duffel's unique identifier for the service
    ///  </summary>
    ///  <example>ase_00009UhD4ongolulWd9123</example>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  The maximum quantity of this service that can be booked with an order
    ///  </summary>
    @JsonProperty("maximum_quantity")
    private Integer maximumQuantity;

    ///  <summary>
    ///  An object containing metadata about the service, like the maximum weight and dimensions of the baggage.
    ///  </summary>
    @JsonProperty("metadata")
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = BaggageMetadata.class, name = ServiceType.BAGGAGE),
            @JsonSubTypes.Type(value = SeatMetadata.class, name = ServiceType.SEAT)
    })
    private Metadata metadata;

    ///  <summary>
    ///  The list of passenger ids the service applies to. If you add this service to an order it will apply to all the
    ///  passengers in this list. For services where the type is baggage, this list will include only a single passenger.
    ///  </summary>
    @JsonProperty("passenger_ids")
    private List<String> passengerIds;

    ///  <summary>
    ///  The list of segment ids the service applies to. If you add this service to an order it will apply to all the
    ///  segments in this list. For services where the type is baggage, depending on the airline, this list includes all
    ///  the segments of all slices or all the segments of a single slice.
    ///  </summary>
    @JsonProperty("segment_ids")
    private List<String> segmentIds;

    ///  <summary>
    ///  The total price of the service for all passengers and segments it applies to, including taxes. This price is
    ///  for a single unit of the service.
    ///  </summary>
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    ///  <summary>
    ///  The currency of the total_amount, as an ISO 4217 currency code. It will match your organisation's billing
    ///  currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
    ///  provided by the airline (which will usually be based on the country where your IATA agency is registered).
    ///  </summary>
    @JsonProperty("total_currency")
    private String totalCurrency;

    ///  <summary>
    ///  The type of the service. For now we only return services of type baggage but we will return other types in the
    ///  future. We won't consider adding new service types a breaking change.
    ///  </summary>
    @JsonProperty("type")
    private ServiceType.Type serviceType;

}
