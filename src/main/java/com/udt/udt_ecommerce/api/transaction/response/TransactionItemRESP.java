package com.udt.udt_ecommerce.api.transaction.response;

import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionItemRESP {
  private Long id;
  private Long productId;
  private ProductModelRESP productModel;
  private int quantity;
  private BigDecimal price;

  public TransactionItemRESP(TransactionItem item) {
    this.id = item.getId();
    this.productId = item.getProductModel().getProduct().getId();
    this.productModel = new ProductModelRESP(item);
    this.quantity = item.getQuantity();
    this.price = item.getPrice();
  }
}
