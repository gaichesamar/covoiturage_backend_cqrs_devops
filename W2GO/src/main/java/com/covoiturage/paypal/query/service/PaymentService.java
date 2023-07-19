package com.covoiturage.paypal.query.service;

import com.covoiturage.paypal.query.entity.PaymentEntity;
import com.covoiturage.paypal.query.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;



import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentEntity getPaymentById(String paymentId) throws NotFoundException {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment not found"));
    }
}

