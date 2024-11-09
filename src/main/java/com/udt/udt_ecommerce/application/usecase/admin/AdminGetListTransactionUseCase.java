package com.udt.udt_ecommerce.application.usecase.admin;

import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminGetListTransactionUseCase {
  private final AdminService adminService;

  public List<TransactionRESP> listTransactions() {
    return adminService.listTransactions();
  }
}
