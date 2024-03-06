package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", order1,
                Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        payments.add(payment1);
        Payment payment2 = new Payment("11111112-1112-1112-1112-111111111112",
                "Cash on Delivery", order2,
                Collections.singletonMap("address", "deliveryFee"));
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        Payment result = paymentService.addPayment(payment.getId(), payment.getMethod(),
                payment.getOrder(), payment.getPaymentData());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExists() {
        Payment payment = payments.get(1);

        Payment samePayment = paymentService.addPayment(payment.getId(), payment.getMethod(),
                payment.getOrder(), payment.getPaymentData());
        assertEquals(payment.getId(), samePayment.getId());
        verify(paymentRepository, times(0)).savePayment(payment);
    }

    @Test
    void testSetStatus() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(),
                payment.getPaymentData(), OrderStatus.SUCCESS.getValue());
        Payment result = paymentService.setStatus(newPayment, "SUCCESS");

        assertEquals(payment.getId(), result.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getStatus());
        }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment,"MEOW"));

    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findByIdPayment(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findByIdPayment("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testFindAllPayment() {
        List<Payment> payments = new ArrayList<>();
        doReturn(payments).when(paymentRepository).findAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }

    @Test
    void testPaymentByVoucherMethod() {
        Payment payment = payments.get(1);
        Payment payment3 = new Payment("11111111-1111-1111-1111-111111111111",
                "PaymentVoucher", payment.getOrder(),
                Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        Payment payment4 = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", payment.getOrder(),
                Collections.singletonMap("voucherCode", "ESHOPABC12345678910"));
        Payment payment5 = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", payment.getOrder(),
                Collections.singletonMap("voucherCode", "ESHOPABCD2345678"));

        payment3 = paymentService.paymentByVoucherMethod(payment3);
        payment4 = paymentService.paymentByVoucherMethod(payment4);
        payment5 = paymentService.paymentByVoucherMethod(payment5);

        assertEquals(payment3.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(payment4.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(payment5.getStatus(), PaymentStatus.REJECTED.getValue());
    }

    @Test
    void testCashonDeliveryMethod() {
        Payment payment = payments.get(1);
        Payment payment6 = new Payment("11111112-1112-1112-1112-111111111112",
                "CashDelivery", payment.getOrder(),
                Collections.singletonMap("address", "deliveryFee"));
        Payment payment7 = new Payment("11111112-1112-1112-1112-111111111112",
                "CashDelivery", payment.getOrder(),
                Collections.singletonMap("a", "deliveryFee"));
        Payment payment8 = new Payment("11111112-1112-1112-1112-111111111112",
                "CashDelivery", payment.getOrder(),
                Collections.singletonMap("address", "a"));

        payment6 = paymentService.cashOnDeliveryMethod(payment6);
        payment7 = paymentService.cashOnDeliveryMethod(payment7);
        payment8 = paymentService.cashOnDeliveryMethod(payment8);

        assertEquals(payment6.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(payment7.getStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(payment8.getStatus(), PaymentStatus.REJECTED.getValue());
    }

}
