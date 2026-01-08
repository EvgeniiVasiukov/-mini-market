package com.minimarket.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StatusController {

    @GetMapping("/status")
    public Map<String, Object> status() {
        Map status = new HashMap();
        status.put("code", 200);
        status.put("message", "OK");
        status.put("timestamp", LocalDateTime.now().toString());
        status.put("application", "gateway");
        return status;
    }
}
