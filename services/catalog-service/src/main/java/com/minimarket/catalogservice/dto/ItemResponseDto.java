package com.minimarket.catalogservice.dto;

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
    private String category;
}
