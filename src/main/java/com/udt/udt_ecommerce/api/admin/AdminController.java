package com.udt.udt_ecommerce.api.admin;

import com.udt.udt_ecommerce.api.admin.dto.request.AdminAgencyCreateREQ;
import com.udt.udt_ecommerce.api.admin.dto.response.AdminAgencyRESP;
import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.api.customer.dto.response.CustomerRESP;
import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.api.shared.BasePaginationResponse;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.application.usecase.admin.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("${url.prefix}/admin")
public class AdminController {
  private final AdminAgencyCreateUseCase adminAgencyCreateUseCase;
  private final AdminGetListBillingUseCase adminGetListBillingUseCase;
  private final AdminGetListCustomerUseCase adminGetListCustomerUseCase;
  private final AdminGetListTransactionUseCase adminGetListTransactionUseCase;
  private final AdminListAgencyUseCase adminListAgencyUseCase;

  @PostMapping("/agencies")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseEntityResponse<AgencyRESP> createAgency(@Valid @RequestBody AdminAgencyCreateREQ req) {
    AgencyRESP createdAgency = adminAgencyCreateUseCase.createAgency(req);
    return BaseEntityResponse.success(createdAgency);
  }

  @GetMapping("/agencies")
  public BasePaginationResponse<AgencyRESP> listAgencies() {
    List<AgencyRESP> agencies = adminListAgencyUseCase.listAgencies();
    return BasePaginationResponse.success(agencies);
  }

  @GetMapping("/customers")
  public BasePaginationResponse<CustomerRESP> listCustomers() {
    List<CustomerRESP> customers = adminGetListCustomerUseCase.listCustomer();
    return BasePaginationResponse.success(customers);
  }

  @GetMapping("/transactions")
  public BasePaginationResponse<TransactionRESP> listTransactions() {
    List<TransactionRESP> transactions = adminGetListTransactionUseCase.listTransactions();
    return BasePaginationResponse.success(transactions);
  }

  @GetMapping("/billing")
  public BasePaginationResponse<BillingRESP> listBilling() {
    List<BillingRESP> billings = adminGetListBillingUseCase.listBilling();
    return BasePaginationResponse.success(billings);
  }
}
