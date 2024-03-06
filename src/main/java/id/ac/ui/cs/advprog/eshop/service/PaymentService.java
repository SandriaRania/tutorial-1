package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    public Payment addPayment(String paymentId, String method, Order order, Map<String, String> paymentdata);
    public Payment setStatus(Payment payment, String status);
    public Payment getPayment(String paymentid);
    public List<Payment> getAllPayments();
    public Payment paymentByVoucherMethod(Payment payment);
    public Payment cashOnDeliveryMethod(Payment payment);

}

