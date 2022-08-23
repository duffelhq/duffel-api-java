package com.duffel.model.response;

import com.duffel.model.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * List of seat maps for an offer or an order.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class SeatMapList extends Data<List<SeatMap>> {

}
