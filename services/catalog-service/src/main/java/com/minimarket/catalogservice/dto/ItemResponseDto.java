package com.minimarket.catalogservice.dto;

import com.minimarket.catalogservice.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemResponseDto {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Boolean isAvailable;
    @Schema(
            description = "Item category",
            example = "BIRDS",
            required = true
    )
    private Category category;
}
