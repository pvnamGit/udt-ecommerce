package com.udt.udt_ecommerce.application.usecase.admin;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminListAgencyUseCase {
  private final AdminService adminService;

  public List<AgencyRESP> listAgencies() {
    return adminService.listAgencies();
  }
}
