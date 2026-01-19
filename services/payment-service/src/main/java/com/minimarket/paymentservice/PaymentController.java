package com.minimarket.paymentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of("status", "OK",
                "application", "payment-service");
    }
}
