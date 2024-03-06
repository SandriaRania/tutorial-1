package id.ac.ui.cs.advprog.eshop.repository;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PaymentInterfaceTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

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
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Bambang Sudrajat");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("11111111-1111-1111-1111-111111111111",
                "Payment by Voucher", order1,
                Collections.singletonMap("voucherCode", "ESHOPABC12345678"));
        payments.add(payment1);
        Payment payment2 = new Payment("11111112-1112-1112-1112-111111111112",
                "Payment by Voucher", order2,
                Collections.singletonMap("voucherCode", "ESHOP12345678ABC"));
        payments.add(payment2);
        Payment payment3 = new Payment("11111113-1113-1113-1113-111111111113",
                "Cash on Delivery", order3,
                Collections.singletonMap("address", "deliveryFee"));
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.savePayment(payment);

        Payment findResult = paymentRepository.findByIdPayment(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.savePayment(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), payment.getOrder(),
                payment.getPaymentData(), PaymentStatus.SUCCESS.getValue());
        Payment result = paymentRepository.savePayment(newPayment);

        Payment findResult = paymentRepository.findByIdPayment(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        Payment findResult = paymentRepository.findByIdPayment(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        Payment findResult = paymentRepository.findByIdPayment("uwu");
        assertNull(findResult);
    }

    @Test
    void testFindAllMethod() {
        for (Payment payment : payments) {
            paymentRepository.savePayment(payment);
        }

        List<Payment> paymentList = paymentRepository.findAllPayment();
        assertEquals(3, paymentList.size());
    }
}