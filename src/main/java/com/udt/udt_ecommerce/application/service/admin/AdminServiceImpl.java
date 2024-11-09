package com.udt.udt_ecommerce.application.service.admin;

import com.udt.udt_ecommerce.api.admin.dto.request.AdminAgencyCreateREQ;
import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.api.customer.dto.response.CustomerRESP;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.application.service.account.request.AccountCreateREQ;
import com.udt.udt_ecommerce.application.service.agency.request.AgencyCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.account.Authority;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.repository.agency.AgencyRepository;
import com.udt.udt_ecommerce.core.service.account.AccountService;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import com.udt.udt_ecommerce.core.service.agency.AgencyService;
import com.udt.udt_ecommerce.core.service.billing.BillingService;
import com.udt.udt_ecommerce.core.service.customer.CustomerService;
import com.udt.udt_ecommerce.core.service.transaction.TransactionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
  private final AccountService accountService;
  private final AgencyRepository agencyRepository;
  private final AgencyService agencyService;
  private final CustomerService customerService;
  private final BillingService billingService;
  private final TransactionService transactionService;

  @Override
  public AgencyRESP createAgency(AdminAgencyCreateREQ req) {
    Account account =
        accountService.createAccount(
            AccountCreateREQ.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .authority(Authority.AGENCY)
                .build());

    Agency agency =
        agencyService.createAgency(
            AgencyCreateREQ.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .gender(req.getGender())
                .phone(req.getPhone())
                .yearOfBirth(req.getYearOfBirth())
                .build(),
            account);
    return new AgencyRESP(agency, account);
  }

  @Override
  public List<AgencyRESP> listAgencies() {
    return agencyRepository.findAll().stream()
        .map(agency -> new AgencyRESP(agency, agency.getAccount()))
        .collect(Collectors.toList());
  }

  @Override
  public List<TransactionRESP> listTransactions() {
    return transactionService.listAllTransactions();
  }

  @Override
  public List<BillingRESP> listBilling() {
    return billingService.listBilling();
  }

  @Override
  public List<CustomerRESP> listCustomers() {
    return customerService.listCustomers().stream()
        .map(customer -> new CustomerRESP(customer, customer.getAccount()))
        .toList();
  }
}
