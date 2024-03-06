package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(String paymentId,  String method, Order order, Map<String, String> paymentData) {
        Payment newPayment = new Payment(paymentId, method, order,
                paymentData);
        paymentRepository.savePayment(newPayment);
        return newPayment;
    }
    @Override
    public Payment setStatus(Payment payment, String status) {
        if (payment != null) {
            Order order = payment.getOrder();
            if (status.equals(PaymentStatus.SUCCESS.getValue())) {
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
                order.setStatus(OrderStatus.FAILED.getValue());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new NoSuchElementException();
        }
        return payment;
    }
    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findByIdPayment(paymentId);
    }
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAllPayment();
    }

    @Override
    public Payment paymentByVoucherMethod(Payment payment) {
        Map map = payment.getPaymentData();
        if (payment.getMethod().equals("Payment by Voucher")) {
            if (map.containsKey("voucherCode")) {
                String code = map.get("voucherCode").toString();
                if (code.length() == 16) {
                    if (code.substring(0, 5).equals("ESHOP")) {
                        int countNumerik = 0;
                        for (char chara : code.toCharArray()) {
                            try {
                                Integer.parseInt(String.valueOf(chara));
                                countNumerik += 1;
                            } catch (NumberFormatException e) {
                            }
                        }
                        if (countNumerik == 8) {
                            payment.setStatus(PaymentStatus.SUCCESS.getValue());
                        } else {
                            payment.setStatus(PaymentStatus.REJECTED.getValue());
                        }
                    } else {
                        payment.setStatus(PaymentStatus.REJECTED.getValue());
                    }
                } else {
                    payment.setStatus(PaymentStatus.REJECTED.getValue());
                }
            } else {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            }
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        }
        return payment;
    }

    @Override
    public Payment cashOnDeliveryMethod(Payment payment) {
        if (payment.getMethod().equals("Cash on Delivery")) {
            Map map = payment.getPaymentData();
            if (map.containsKey("address")) {
                String code = map.get("address").toString();
                if (code.equals("deliveryFee")) {
                    payment.setStatus(PaymentStatus.SUCCESS.getValue());
                } else {
                    payment.setStatus(PaymentStatus.REJECTED.getValue());
                }
            } else {
                payment.setStatus(PaymentStatus.REJECTED.getValue());
            }
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
        }
        return payment;
    }

}
