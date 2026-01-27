package com.minimarket.catalogservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
    @NotBlank
    @Size(min = 4, max = 50)
    private String name;
    private String description;
    @NotBlank
    private String imageUrl;
    @NotNull
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "9999.00")
    private BigDecimal price;
    @NotBlank
    private String category;
    @NotNull
    private Boolean isAvailable;
}
