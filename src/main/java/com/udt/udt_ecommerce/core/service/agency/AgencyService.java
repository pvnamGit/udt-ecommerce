package com.udt.udt_ecommerce.core.service.agency;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.application.service.agency.request.AgencyCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.agency.Agency;

import java.util.List;

public interface AgencyService {
    Agency createAgency(AgencyCreateREQ dto, Account account);
    List<AgencyRESP> listAgencies();
    AgencyRESP agencyDetail(Long id);
}
