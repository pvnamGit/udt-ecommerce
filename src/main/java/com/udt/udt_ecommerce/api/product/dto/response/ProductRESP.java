package com.udt.udt_ecommerce.api.product.dto.response;

import com.udt.udt_ecommerce.core.entity.product.Product;
import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductRESP {
    private Long id;
    private String name;
    private String description;
    private Long agencyId;
    private List<ProductModelRESP> productModels;

    public ProductRESP(Product product, List<ProductModel> productModels) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.agencyId = product.getAgency().getId();
        this.productModels = productModels.stream()
                .map(ProductModelRESP::new)
                .collect(Collectors.toList());
    }
}
