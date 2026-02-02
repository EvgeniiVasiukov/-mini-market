package com.minimarket.catalogservice.controller;

import com.minimarket.catalogservice.dto.ItemRequestDto;
import com.minimarket.catalogservice.dto.ItemResponseDto;
import com.minimarket.catalogservice.dto.ItemUpdateRequestDto;
import com.minimarket.catalogservice.exception.ApiError;
import com.minimarket.catalogservice.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final ItemService itemService;
    public CatalogController(ItemService itemService) {
        this.itemService = itemService;
    }

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

    @Operation(summary = "Get item by id",
    description = "Returns item by its id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Item found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(
                responseCode = "404",
                description = "Item not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid id format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/items/{id}")
    public ItemResponseDto getCatalogItem(@PathVariable("id") Long id) {

        return itemService.getItemById(id);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponseDto createCatalogItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        return itemService.createItem(itemRequestDto);
    }

    @PutMapping("/items/{id}")
    public ItemResponseDto updateCatalogItem(@RequestBody ItemRequestDto itemRequestDto,
                                             @PathVariable("id") Long id) {
        return itemService.updateItem(id, itemRequestDto);
    }

    @PatchMapping("/items/{id}")
            public ItemResponseDto partiallyUpdateCatalogItem(@RequestBody ItemUpdateRequestDto itemUpdateRequestDto,
                                                              @PathVariable("id") Long id) {
        return itemService.patchItem(id, itemUpdateRequestDto);
    }

    @DeleteMapping("items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCatalogItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }

}
