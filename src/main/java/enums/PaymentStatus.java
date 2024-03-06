package enums;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import lombok.Getter;

@Getter
public enum PaymentStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;
    private PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }

}
