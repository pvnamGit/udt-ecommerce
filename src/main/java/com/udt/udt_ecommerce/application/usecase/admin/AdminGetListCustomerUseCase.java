package com.udt.udt_ecommerce.application.usecase.admin;

import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.api.customer.dto.response.CustomerRESP;
import com.udt.udt_ecommerce.core.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminGetListCustomerUseCase {
    private final AdminService adminService;

    public List<CustomerRESP> listCustomer() {
        return adminService.listCustomers();
    }
}
