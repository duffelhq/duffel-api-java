package com.duffel.model;

/**
 * Destination for a refund. Airlines support different refund destinations.
 */
public enum RefundDestination {

    arc_bsp_cash,
    balance,
    card,
    voucher,
    awaiting_payment,
    original_form_of_payment

}
