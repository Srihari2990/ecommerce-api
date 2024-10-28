package com.personalportfoli0.ecommerce_api.controller;

import com.personalportfoli0.ecommerce_api.service.PaymentService;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-session")
    public Session createPaymentSession(@RequestBody Map<String, Object> data) {
        double amount = (double) data.get("amount");
        try {
            // Calls the PaymentService to create a new payment session with the specified amount
            return paymentService.createPaymentSession(amount);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create payment session", e);
        }
    }
}
