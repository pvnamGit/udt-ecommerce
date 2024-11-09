package com.udt.udt_ecommerce.api.billing;

import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.api.shared.BasePaginationResponse;
import com.udt.udt_ecommerce.application.usecase.billing.BillingListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${url.prefix}/billing")
@AllArgsConstructor
public class BillingController {

  private final BillingListUseCase billingListUseCase;

  @GetMapping("")
  @PreAuthorize("hasAuthority('CUSTOMER')")
  public BasePaginationResponse listBilling() {
    List<BillingRESP> billing = billingListUseCase.listAllBilling();
    return BasePaginationResponse.success(billing, billing.size());
  }
}
