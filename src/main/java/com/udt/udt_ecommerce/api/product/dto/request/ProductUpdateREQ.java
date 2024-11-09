package com.udt.udt_ecommerce.api.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductUpdateREQ {
    private String name;
    private String description;
}
