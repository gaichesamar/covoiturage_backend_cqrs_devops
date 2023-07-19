package com.covoiturage.paypal.query.controllers;

import com.covoiturage.paypal.query.entity.PaymentEntity;
import com.covoiturage.paypal.query.service.PaymentService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class ContollerQuery{

    private final PaymentService paymentService;

    public ContollerQuery(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentEntity> getPayment(@PathVariable String paymentId) throws NotFoundException {
        PaymentEntity paymentDto = paymentService.getPaymentById(paymentId);
        if (paymentDto != null) {
            return ResponseEntity.ok(paymentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
