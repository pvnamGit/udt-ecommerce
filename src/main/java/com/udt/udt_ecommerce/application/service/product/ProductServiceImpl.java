package com.udt.udt_ecommerce.application.service.product;

import com.udt.udt_ecommerce.api.product.dto.request.ProductCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelCreateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductModelUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.request.ProductUpdateREQ;
import com.udt.udt_ecommerce.api.product.dto.response.ProductModelRESP;
import com.udt.udt_ecommerce.api.product.dto.response.ProductRESP;
import com.udt.udt_ecommerce.core.entity.account.SecurityCurrentUser;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.product.Product;
import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import com.udt.udt_ecommerce.core.repository.product.ProductModelRepository;
import com.udt.udt_ecommerce.core.repository.product.ProductRepository;
import com.udt.udt_ecommerce.core.service.product.ProductService;
import com.udt.udt_ecommerce.shared.helper.GenericPatcher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductModelRepository productModelRepository;
  private final SecurityCurrentUser currentUser;
  @PersistenceContext private EntityManager entityManager;

  @Override
  public ProductRESP createProduct(ProductCreateREQ req) {
    Agency agency =
        entityManager.getReference(Agency.class, currentUser.getCurrentUser().getAgencyId());

    Product product =
        Product.builder()
            .name(req.getName())
            .description((req.getDescription()))
            .agency(agency)
            .build();
    productRepository.saveAndFlush(product);

    List<ProductModel> productModels =
        req.getProductModels().stream()
            .map(
                productModelReq -> {
                  return ProductModel.builder()
                      .price(productModelReq.getPrice())
                      .size(productModelReq.getSize())
                      .color(productModelReq.getColor())
                      .stockQuantity(productModelReq.getStockQuantity())
                      .description(productModelReq.getDescription())
                      .name(productModelReq.getName())
                      .product(product)
                      .build();
                })
            .toList();
    productModelRepository.saveAll(productModels);
    return new ProductRESP(product, productModels);
  }

  @Override
  public ProductRESP detail(Long id) {
    Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return new ProductRESP(product, product.getProductModels());
  }

  @Override
  public List<ProductRESP> list() {
    return productRepository.findAll().stream()
        .map(product -> new ProductRESP(product, product.getProductModels()))
        .collect(Collectors.toList());
  }

  @Override
  public ProductRESP updateProduct(Long productId, ProductUpdateREQ req) {
    Product product =
        productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    this.authorizedProductOwnership(product.getAgency().getId());

    Product updatedProduct =
        Product.builder().name(req.getName()).description((req.getDescription())).build();

    GenericPatcher<Product> patcher = new GenericPatcher<>();
    patcher.patch(product, updatedProduct);
    product.setUpdatedAt(Instant.now().toEpochMilli());
    productRepository.save(product);
    return new ProductRESP(product, product.getProductModels());
  }

  @Override
  public ProductModelRESP createProductModel(Long productId, ProductModelCreateREQ req) {
    Product product =
        productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    this.authorizedProductOwnership(product.getAgency().getId());

    ProductModel productModel =
        ProductModel.builder()
            .price(req.getPrice())
            .size(req.getSize())
            .color(req.getColor())
            .stockQuantity(req.getStockQuantity())
            .description(req.getDescription())
            .name(req.getName())
            .product(product)
            .build();
    productModelRepository.saveAndFlush(productModel);
    return new ProductModelRESP(productModel);
  }

  @Override
  @SneakyThrows
  public ProductModelRESP updateProductModel(
      Long productId, Long productModelId, ProductModelUpdateREQ req) throws ConstraintViolationException {
    Product product =
        productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    this.authorizedProductOwnership(product.getAgency().getId());

    ProductModel productModel =
        productModelRepository.findById(productModelId).orElseThrow(EntityNotFoundException::new);

    if (!productModel.getProduct().getId().equals(productId))
      throw new DataIntegrityViolationException("Product model not belong to product");

    ProductModel updatedProductModel =
        ProductModel.builder()
            .price(req.getPrice())
            .size(req.getSize())
            .color(req.getColor())
            .stockQuantity(req.getStockQuantity())
            .description(req.getDescription())
            .name(req.getName())
            .product(product)
            .build();

    GenericPatcher<ProductModel> patcher = new GenericPatcher<>();
    patcher.patch(productModel, updatedProductModel);
    productModel.setUpdatedAt(Instant.now().toEpochMilli());
    productModelRepository.save(productModel);
    return new ProductModelRESP(productModel);
  }

  @Override
  public void deleteProduct(Long id) {
    Product product =
            productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    this.authorizedProductOwnership(product.getAgency().getId());

    productModelRepository.deleteAll(product.getProductModels());
    productRepository.delete(product);
  }

  @Override
  public void deleteProductModel(Long productId, Long productModelId) {
    Product product =
            productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    this.authorizedProductOwnership(product.getAgency().getId());
    ProductModel productModel =
            productModelRepository.findById(productModelId).orElseThrow(EntityNotFoundException::new);
    productModelRepository.delete(productModel);
  }

  private void authorizedProductOwnership(Long productAgencyId) {
    if (!Objects.equals(productAgencyId, currentUser.getCurrentUser().getAgencyId()))
      throw new AccessDeniedException("Only product owner can do this action");
  }
}
