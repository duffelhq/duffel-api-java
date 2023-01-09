package com.duffel.model.response;

/**
 * Type of service.
 */
public class ServiceType {

    public static final String BAGGAGE = "baggage";
    public static final String SEAT = "seat";
    public static final String CANCEL_FOR_ANY_REASON = "cancel_for_any_reason";

    public enum Type {
        baggage(BAGGAGE),
        seat(SEAT),
        cancel_for_any_reason(CANCEL_FOR_ANY_REASON);

        private final String serviceTypeEnum;

        Type(String serviceTypeEnum) {
            this.serviceTypeEnum = serviceTypeEnum;
        }

        public String getServiceType() {
            return serviceTypeEnum;
        }
    }
}
