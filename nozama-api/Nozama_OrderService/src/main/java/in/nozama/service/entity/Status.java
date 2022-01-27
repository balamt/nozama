package in.nozama.service.entity;

/**
 * Order, Shipment, Payment Status codes in one ENUM
 */
public enum Status {

    NEW(300,"NEW"),
    ORDER_CANCELLED(301,"ORDER_CANCELLED"),
    REFUND_INPROGRESS(302, "REFUND_INPROGRESS"),
    REFUND_DONE(303, "REFUND_DONE"),

    PAYMENT_PENDING(400,"PAYMENT_PENDING"),
    PAYMENT_SUCCESS(401,"PAYMENT_SUCCESS"),
    PAYMENT_FAILED(402, "PAYMENT_FAILED"),

    SHIP_CREATED(500,"SHIP_CREATED"),
    SHIP_READY(501,"SHIP_READY"),
    SHIP_TRANSMIT(502, "SHIP_TRANSMIT"),
    SHIP_OUT_FOR_DELIVERY(503, "SHIP_OUT_FOR_DELIVERY"),
    SHIP_RETURNED(504, "SHIP_RETURNED"),
    SHIP_DELIVERED(505, "SHIP_DELIVERED"),

    COMPLETED(100, "COMPLETED");

    private Integer code;
    private String description;

    Status(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public Integer code(){
        return this.code;
    }
    public String description(){
        return this.description;
    }


}
