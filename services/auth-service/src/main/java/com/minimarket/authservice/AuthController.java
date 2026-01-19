package com.minimarket.authservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of(
                "service", "auth-service",
                "status", "ok"
        );
    }
}