package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentTest {

    private Order order;
    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentEmptyProduct() {
        order.clearOrder();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("11111111-1111-1111-1111-111111111111", 
                    "Payment by Voucher", this.order, 
                            Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Payment payment = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", this.order, Collections.singletonMap("voucherCode", "ESHOPABC12345678"));

        assertSame(this.order, payment.getOrder());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getOrder().getId());
        assertEquals(order.getProducts(), payment.getOrder().getProducts());
        assertEquals(order.getOrderTime(), payment.getOrder().getOrderTime());
        assertEquals(order.getAuthor(), payment.getOrder().getAuthor());

        assertEquals("11111111-1111-1111-1111-111111111111", payment.getId());
        assertEquals("Payment by Voucher", payment.getMethod());
        assertEquals(Collections.singletonMap("voucherCode", "ESHOPABC12345678"), payment.getPaymentData());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", this.order, Collections.singletonMap("voucherCode", "ESHOPABC12345678"), "SUCCESS");
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("11111111-1111-1111-1111-111111111111",
                    "Payment by Voucher", this.order, Collections.singletonMap("voucherCode", "ESHOPABC12345678"), "MIAUW :3");
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", this.order, Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", this.order, Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEONG :3"));
    }
}
