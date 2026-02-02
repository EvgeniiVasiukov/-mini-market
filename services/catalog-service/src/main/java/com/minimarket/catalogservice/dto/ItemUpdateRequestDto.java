package com.minimarket.catalogservice.dto;

import com.minimarket.catalogservice.entity.Category;
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
            example = "BIRDS",
            required = true
    )
    private Category category;
    private Boolean isAvailable;
}
