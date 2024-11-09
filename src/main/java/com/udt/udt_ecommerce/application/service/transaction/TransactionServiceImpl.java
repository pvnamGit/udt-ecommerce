package com.udt.udt_ecommerce.application.service.transaction;

import com.udt.udt_ecommerce.api.transaction.request.TransactionCreateREQ;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.application.service.billing.request.BillingCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.account.SecurityCurrentUser;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.entity.product.ProductModel;
import com.udt.udt_ecommerce.core.entity.transaction.Transaction;
import com.udt.udt_ecommerce.core.entity.transaction.TransactionItem;
import com.udt.udt_ecommerce.core.repository.product.ProductModelRepository;
import com.udt.udt_ecommerce.core.repository.transaction.TransactionItemRepository;
import com.udt.udt_ecommerce.core.repository.transaction.TransactionRepository;
import com.udt.udt_ecommerce.core.service.billing.BillingService;
import com.udt.udt_ecommerce.core.service.transaction.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
  private final ProductModelRepository productModelRepository;
  private final TransactionItemRepository transactionItemRepository;
  private final SecurityCurrentUser securityCurrentUser;
  private final BillingService billingService;
  @PersistenceContext EntityManager entityManager;

  @Override
  public List<TransactionRESP> listAllTransactions() {
    // Ensure the current user is authenticated
    var currentUser = securityCurrentUser.getCurrentUser();
    if (currentUser == null) throw new AccessDeniedException("Access Denied");

    // Fetch transactions based on user authority
    List<Transaction> transactions =
        switch (currentUser.getAuthority()) {
          case ADMIN -> transactionRepository.findAll();
          case AGENCY ->
              transactionRepository.findAllByAgencyId(currentUser.getAgencyId()).stream()
                  // Filter transaction items by agency ID to ensure only agency-related products
                  // are included
                  .peek(
                      transaction ->
                          transaction.setTransactionItems(
                              transaction.getTransactionItems().stream()
                                  .filter(
                                      ti ->
                                          ti.getProductModel()
                                              .getProduct()
                                              .getAgency()
                                              .getId()
                                              .equals(currentUser.getAgencyId()))
                                  .toList()))
                  .toList();

          case CUSTOMER -> transactionRepository.findAllByAccountId(currentUser.getAccountId());

          default -> throw new AccessDeniedException("Access Denied");
        };

    // Convert transactions to response DTOs
    return transactions.stream()
        .map(transaction -> new TransactionRESP(transaction, transaction.getTransactionItems()))
        .toList();
  }

  @Override
  public TransactionRESP createTransaction(TransactionCreateREQ req) {
    Long accountId = securityCurrentUser.getCurrentUser().getAccountId();
    Account account = entityManager.getReference(Account.class, accountId);

    BigDecimal totalAmount = req.calculateTotalAmount();

    Billing billing =
        billingService.createBilling(
            BillingCreateREQ.builder()
                .amount(totalAmount)
                .paymentMethod(req.getPaymentMethod())
                .paymentStatus(req.getPaymentStatus())
                .build());

    Transaction transaction =
        Transaction.builder()
            .account(account)
            .transactionAt(System.currentTimeMillis())
            .billing(billing)
            .totalAmount(totalAmount)
            .build();
    transactionRepository.save(transaction);

    // Fetch all ProductModel references in one query
    Map<Long, ProductModel> productModelMap =
        productModelRepository
            .findAllById(
                req.getItems().stream()
                    .map(TransactionCreateREQ.TransactionItemREQ::getProductModelId)
                    .collect(Collectors.toSet()))
            .stream()
            .collect(Collectors.toMap(ProductModel::getId, pm -> pm));

    // Map items to transaction items
    List<TransactionItem> transactionItems =
        req.getItems().stream()
            .map(
                item -> {
                  ProductModel productModel = productModelMap.get(item.getProductModelId());
                  if (productModel == null) {
                    throw new EntityNotFoundException(
                        "ProductModel not found for id: " + item.getProductModelId());
                  }
                  Customer customer =
                      entityManager.getReference(
                          Customer.class, securityCurrentUser.getCurrentUser().getCustomerId());
                  Agency agency =
                      entityManager.getReference(
                          Agency.class, productModel.getProduct().getAgency().getId());

                  return TransactionItem.builder()
                      .price(item.getPrice())
                      .customer(customer)
                      .agency(agency)
                      .quantity(item.getQuantity())
                      .transaction(transaction)
                      .productModel(productModel)
                      .build();
                })
            .toList();

    transactionItemRepository.saveAll(transactionItems); // Batch save all transaction items

    return new TransactionRESP(transaction, transactionItems);
  }
}
