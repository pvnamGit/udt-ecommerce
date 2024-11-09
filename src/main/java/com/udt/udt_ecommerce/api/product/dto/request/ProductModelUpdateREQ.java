package com.udt.udt_ecommerce.api.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModelUpdateREQ {
    private String name;
    private String description;
    private Integer stockQuantity;
    private BigDecimal price;
    private String color;
    private Integer size;
}
