package com.minimarket.catalogservice.dto;

import com.minimarket.catalogservice.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @NotNull
    @Schema(
            description = "Item category",
            example = "BIRDS",
            required = true
    )
    private Category category;
    @NotNull
    private Boolean isAvailable;
}
