package com.minimarket.catalogservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Nullable
public class ItemUpdateRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    @Schema(
            description = "Item category",
            allowableValues = {"BIRDS", "MAMMALS", "REPTILES", "FISH", "OTHER"},
            example = "BIRDS",
            required = true
    )
    private String category;
    private Boolean isAvailable;
}
