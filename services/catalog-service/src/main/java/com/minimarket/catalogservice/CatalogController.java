package com.minimarket.catalogservice;

import com.minimarket.catalogservice.dto.ItemRequestDto;
import com.minimarket.catalogservice.dto.ItemResponseDto;
import com.minimarket.catalogservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final ItemService itemService;

    @GetMapping("/items")
    public Page <ItemResponseDto> getCatalogItems(
            @RequestParam(required = false)
            String category,
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            BigDecimal minPrice,
            @RequestParam(required = false)
            BigDecimal maxPrice,
            @RequestParam(required = false)
            Boolean isAvailable,
            Pageable pageable
    ) {
        return itemService.getItems(category, name, isAvailable, minPrice, maxPrice, pageable);
    }

    @GetMapping("/items/{id}")
    public ItemResponseDto getCatalogItem(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping("/items")
    public ItemResponseDto createCatalogItem(@RequestBody ItemRequestDto itemRequestDto) {
        return itemService.createItem(itemRequestDto);
    }

    @PutMapping("/items/{id}")
    public ItemResponseDto updateCatalogItem(@RequestBody ItemRequestDto itemRequestDto,
                                             @PathVariable("id") Long id) {
        return itemService.updateItem(id, itemRequestDto);
    }

}
