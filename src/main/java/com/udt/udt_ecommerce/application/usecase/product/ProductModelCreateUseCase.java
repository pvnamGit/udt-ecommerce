package com.udt.udt_ecommerce.application.usecase.product;

import com.udt.udt_ecommerce.api.product.dto.request.ProductModelCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.core.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductModelCreateUseCase {
    private final ProductService productService;

    public ProductModelRESP createProductModel(Long productId, ProductModelCreateREQ req) {
        return productService.createProductModel(productId, req);
    }
}
