package com.udt.udt_ecommerce.api.product.dto.response;

import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductModelRESP {
    private Long id;
    private String name;
    private String description;
    private Integer stockQuantity;
    private BigDecimal price;
    private String color;
    private Integer size;

    public ProductModelRESP(ProductModel productModel) {
        this.id = productModel.getId();
        this.name = productModel.getName();
        this.description = productModel.getDescription();
        this.stockQuantity = productModel.getStockQuantity();
        this.price = productModel.getPrice();
        this.color = productModel.getColor();
        this.size = productModel.getSize();
    }

    public ProductModelRESP(TransactionItem transactionItem) {
        this.id = transactionItem.getProductModel().getId();
        this.name = transactionItem.getProductModel().getName();
        this.description = transactionItem.getProductModel().getDescription();
        this.stockQuantity = transactionItem.getProductModel().getStockQuantity();
        this.price = transactionItem.getProductModel().getPrice();
        this.color = transactionItem.getProductModel().getColor();
        this.size = transactionItem.getProductModel().getSize();
    }
}
