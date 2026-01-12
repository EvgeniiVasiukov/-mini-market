package com.minimarket.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class CatalogController {
    @GetMapping("/api/v1/catalog/items")
    public Map<String, String> getCatalogItems() {
        return Map.of("items", "item1", "item2", "item3");
    }
    @GetMapping("/api/v1/catalog/items/{id}")
    public Map<String, String> getCatalogItem(@PathVariable("id") String id) {
        return Map.of("item", id);
    }

}
