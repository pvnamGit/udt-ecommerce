package com.udt.udt_ecommerce.api.product;

import com.udt.udt_ecommerce.api.product.dto.request.ProductCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.api.shared.BasePaginationResponse;
import com.udt.udt_ecommerce.application.usecase.product.*;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.prefix}/products")
@AllArgsConstructor
public class ProductController {
  private final ProductCreateUseCase productCreateUseCase;
  private final ProductListUseCase productListUseCase;
  private final ProductGetDetailUseCase productGetDetailUseCase;
  private final ProductDeleteUseCase productDeleteUseCase;
  private final ProductUpdateUseCase productUpdateUseCase;
  private final ProductModelCreateUseCase productModelCreateUseCase;
  private final ProductModelUpdateUseCase productModelUpdateUseCase;
  private final ProductModelDeleteUseCase productModelDeleteUseCase;

  @PostMapping("")
  @PreAuthorize("hasAuthority('AGENCY')")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseEntityResponse<ProductRESP> createProduct(@Valid @RequestBody ProductCreateREQ req) {
    ProductRESP product = productCreateUseCase.createProduct(req);
    return BaseEntityResponse.success(product);
  }

  @GetMapping("")
  public BasePaginationResponse listProducts() {
    List<ProductRESP> products = productListUseCase.listProducts();
    return BasePaginationResponse.success(products, products.size());
  }

  @GetMapping("/{id}")
  @SneakyThrows
  public BaseEntityResponse<ProductRESP> detail(@PathVariable(name = "id") Long id) {
    ProductRESP product = productGetDetailUseCase.productGetDetail(id);
    return BaseEntityResponse.success(product);
  }

  @PatchMapping("/{id}")
  @SneakyThrows
  @PreAuthorize("hasAuthority('AGENCY')")
  public BaseEntityResponse<ProductRESP> updateProduct(
      @PathVariable(name = "id") Long id, @Valid @RequestBody ProductUpdateREQ req) {
    ProductRESP product = productUpdateUseCase.updateProduct(id, req);
    return BaseEntityResponse.success(product);
  }

  @DeleteMapping("/{id}")
  @SneakyThrows
  @PreAuthorize("hasAuthority('AGENCY')")
  public BaseEntityResponse deleteProduct(@PathVariable(name = "id") Long id) {
    productDeleteUseCase.deleteProduct(id);
    return BaseEntityResponse.success();
  }

  @PostMapping("/{id}/models")
  @PreAuthorize("hasAuthority('AGENCY')")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseEntityResponse<ProductModelRESP> createProductModel(
      @PathVariable(name = "id") Long id, @Valid @RequestBody ProductModelCreateREQ req) {
    ProductModelRESP productModel = productModelCreateUseCase.createProductModel(id, req);
    return BaseEntityResponse.success(productModel);
  }

  @PatchMapping("/{productId}/models/{productModelId}")
  @PreAuthorize("hasAuthority('AGENCY')")
  @ResponseStatus(HttpStatus.CREATED)
  @SneakyThrows
  public BaseEntityResponse<ProductModelRESP> createProductModel(
      @PathVariable(name = "productId") Long productId,
      @PathVariable(name = "productModelId") Long productModelId,
      @Valid @RequestBody ProductModelUpdateREQ req) {
    ProductModelRESP productModel =
        productModelUpdateUseCase.updateProductModel(productId, productModelId, req);
    return BaseEntityResponse.success(productModel);
  }

  @DeleteMapping("/{productId}/models/{productModelId}")
  @PreAuthorize("hasAuthority('AGENCY')")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseEntityResponse<ProductModelRESP> createProductModel(
      @PathVariable(name = "productId") Long productId,
      @PathVariable(name = "productModelId") Long productModelId) {
    productModelDeleteUseCase.deleteProductModel(productId, productModelId);
    return BaseEntityResponse.success();
  }
}
