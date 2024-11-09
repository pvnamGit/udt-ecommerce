package com.udt.udt_ecommerce.application.usecase.billing;

import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.service.billing.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BillingListUseCase {
  private final BillingService billingService;

  public List<BillingRESP> listAllBilling() {
    return billingService.listBilling();
  }
}
