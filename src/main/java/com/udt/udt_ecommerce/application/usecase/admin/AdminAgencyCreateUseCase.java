package com.udt.udt_ecommerce.application.usecase.admin;

import com.udt.udt_ecommerce.api.admin.dto.request.AdminAgencyCreateREQ;
import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminAgencyCreateUseCase {
  private final AdminService adminService;

  public AgencyRESP createAgency(AdminAgencyCreateREQ req) {
    return adminService.createAgency(req);
  }
}
