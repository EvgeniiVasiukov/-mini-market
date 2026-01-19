package com.minimarket.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @GetMapping("/status")
    public Map<String, String> getOrderStatus() {
        return Map.of("status", "OK","application", "order-service");
    }
}
