package com.udt.udt_ecommerce.api.agency;

import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.api.shared.BasePaginationResponse;
import com.udt.udt_ecommerce.application.usecase.agency.AgencyGetDetailUseCase;
import com.udt.udt_ecommerce.application.usecase.agency.AgencyListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("${url.prefix}/agencies")
public class AgencyController {
    private final AgencyGetDetailUseCase agencyGetDetailUseCase;
    private final AgencyListUseCase agencyListUseCase;

    @GetMapping("")
    public BasePaginationResponse list() {
        List<AgencyRESP> agencies = agencyListUseCase.list();
        return BasePaginationResponse.success(agencies);
    }
    @GetMapping("/{id}")
    public BaseEntityResponse<AgencyRESP> detail( @PathVariable(name = "id") Long id) {
        AgencyRESP agency = agencyGetDetailUseCase.detail(id);
        return BaseEntityResponse.success(agency);
    }
}
