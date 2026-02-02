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
                            schema = @Schema(ref = "#/components/schemas/ApiError"))),
            @ApiResponse(
                responseCode = "400",
                description = "Invalid id format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")))
    })
    @GetMapping("/items/{id}")
    public ItemResponseDto getCatalogItem(@PathVariable("id") Long id) {

        return itemService.getItemById(id);
    }

    @Operation(
            summary = "Create a new item in catalog",
            description = "Admin user can create new items in catalog. Name, price, category, imageUrl, availability are essential, description is optional",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = ItemRequestDto.class))
            ),
    responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Item successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponseDto.class)
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed for request body",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            )
    })
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponseDto createCatalogItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        return itemService.createItem(itemRequestDto);
    }

    @Operation(
            summary = "Updates all fields",
            description = "Admin can change all of the fields in the entry",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = ItemRequestDto.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed for request body",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            )
    })
    @PutMapping("/items/{id}")
    public ItemResponseDto updateCatalogItem(@Valid @RequestBody ItemRequestDto itemRequestDto,
                                             @PathVariable("id") Long id) {
        return itemService.updateItem(id, itemRequestDto);
    }


    @Operation(
            summary = "Updates only given fields",
            description = "Admin can change one or several fields in the entry",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = ItemUpdateRequestDto.class))
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed for request body",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            )
    })
    @PatchMapping("/items/{id}")
            public ItemResponseDto partiallyUpdateCatalogItem(@RequestBody ItemUpdateRequestDto itemUpdateRequestDto,
                                                              @PathVariable("id") Long id) {
        return itemService.patchItem(id, itemUpdateRequestDto);
    }

    @Operation(
            summary = "Deletes item with given id",
            description = "Admin can delete item from catalogue"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Item successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id format",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item with given id was not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/ApiError")
                    )
            )

    })
    @DeleteMapping("items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCatalogItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }

}
