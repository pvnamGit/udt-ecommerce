package com.udt.udt_ecommerce.application.usecase.admin;

import com.udt.udt_ecommerce.api.admin.dto.request.AdminAgencyCreateREQ;
import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.core.entity.billing.Billing;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminGetListBillingUseCase {
    private final AdminService adminService;

    public List<BillingRESP> listBilling() {
        return adminService.listBilling();
    }
}
