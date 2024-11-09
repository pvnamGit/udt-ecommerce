package com.udt.udt_ecommerce.application.usecase.agency;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.core.service.agency.AgencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyGetDetailUseCase {
  private final AgencyService agencyService;

  public AgencyRESP detail(Long id) {
    return agencyService.agencyDetail(id);
  }
}
