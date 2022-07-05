package com.duffel.model.response;

import com.duffel.model.Data;
import com.duffel.model.response.seatmap.Cabin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class SeatMapSet extends Data<List<SeatMap>> {

}
