package com.udt.udt_ecommerce.application.usecase.agency;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.core.service.agency.AgencyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyListUseCase {
    private final AgencyService agencyService;

    public List<AgencyRESP> list() {
        return agencyService.listAgencies();
    }
}
