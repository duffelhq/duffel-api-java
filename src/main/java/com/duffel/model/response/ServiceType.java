package com.duffel.model.response;

/**
 * Type of service.
 */
public class ServiceType {

    public static final String BAGGAGE = "baggage";
    public static final String SEAT = "seat";

    public enum Type {
        baggage(BAGGAGE),
        seat(SEAT);

        private final String serviceTypeEnum;

        Type(String serviceTypeEnum) {
            this.serviceTypeEnum = serviceTypeEnum;
        }

        public String getServiceType() {
            return serviceTypeEnum;
        }
    }
}
