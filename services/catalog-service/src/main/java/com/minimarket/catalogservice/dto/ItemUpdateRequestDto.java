package com.minimarket.catalogservice.dto;

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
    private String category;
    private Boolean isAvailable;
}
