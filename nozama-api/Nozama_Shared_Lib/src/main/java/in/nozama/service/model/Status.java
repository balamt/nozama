package in.nozama.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Status {
	COMPLETED(100, "COMPLETED"),
	FAILED(101, "Failed"),
	PROBLEM(102, "Problem"),
	PROCESSING(103, "Processing"),
	HOLD(104, "Hold"),
	SUCCESS(105, "Success"),
	ERROR(106, "Error"),
	
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
    SHIP_DELIVERED(505, "SHIP_DELIVERED");

	@Getter private Integer code;
	@Getter private String value;
}
