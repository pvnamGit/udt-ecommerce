package com.udt.udt_ecommerce.application.service.agency;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.application.service.agency.request.AgencyCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.repository.agency.AgencyRepository;
import com.udt.udt_ecommerce.core.service.agency.AgencyService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {
  private final AgencyRepository agencyRepository;

  @Override
  public Agency createAgency(AgencyCreateREQ dto, Account account) {
    Agency agency =
        Agency.builder()
            .account(account)
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .gender(dto.getGender())
            .phone(dto.getPhone())
            .yearOfBirth(dto.getYearOfBirth())
            .build();
    agencyRepository.saveAndFlush(agency);
    return agency;
  }

  @Override
  public List<AgencyRESP> listAgencies() {
    return agencyRepository.findAll().stream()
        .map(agency -> new AgencyRESP(agency, agency.getAccount()))
        .toList();
  }

  @Override
  public AgencyRESP agencyDetail(Long id) {
    Agency agency = agencyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return new AgencyRESP(agency, agency.getAccount());
  }
}
