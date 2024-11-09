package com.udt.udt_ecommerce.core.service.product;

import com.udt.udt_ecommerce.api.product.dto.request.ProductCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
  ProductRESP createProduct(ProductCreateREQ req);

  ProductRESP detail(Long id);

  List<ProductRESP> list();

  ProductRESP updateProduct(Long productId, ProductUpdateREQ req);

  ProductModelRESP createProductModel(Long productId, ProductModelCreateREQ req);

  ProductModelRESP updateProductModel(
      Long productId, Long productModelId, ProductModelUpdateREQ req)
      throws DataIntegrityViolationException;

  void deleteProduct(Long id);

  void deleteProductModel(Long productId, Long productModelId);
}
