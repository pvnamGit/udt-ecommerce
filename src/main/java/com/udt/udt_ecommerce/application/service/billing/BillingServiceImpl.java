package com.udt.udt_ecommerce.application.service.billing;

import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.application.service.billing.request.BillingCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.SecurityCurrentUser;
import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.repository.billing.BillingRepository;
import com.udt.udt_ecommerce.core.service.billing.BillingService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillingServiceImpl implements BillingService {
  private final BillingRepository billingRepository;
  private final SecurityCurrentUser currentUser;

  @Override
  public List<BillingRESP> listBilling() {
    if (currentUser.getCurrentUser() == null) throw new AccessDeniedException("Access Denied");
    List<Billing> billing = new ArrayList<>();
    switch (currentUser.getCurrentUser().getAuthority()) {
      case ADMIN -> billing = billingRepository.findAll();
      case CUSTOMER ->
          billing =
              billingRepository.findBillingsByCustomer(currentUser.getCurrentUser().getAccountId());
      default -> throw new AccessDeniedException("Access Denied");
    }
    return billing.stream().map(BillingRESP::new).toList();
  }

  @Override
  public Billing createBilling(BillingCreateREQ dto) {
    Billing billing =
        Billing.builder()
            .amount(dto.getAmount())
            .paymentAt(System.currentTimeMillis())
            .paymentMethod(dto.getPaymentMethod())
            .status(dto.getPaymentStatus())
            .build();
    billingRepository.saveAndFlush(billing);
    return billing;
  }
}
