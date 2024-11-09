package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductListUseCase {
    private final ProductService productService;

    @SneakyThrows
    public List<ProductRESP> listProducts() {
        return productService.list();
    }
}
