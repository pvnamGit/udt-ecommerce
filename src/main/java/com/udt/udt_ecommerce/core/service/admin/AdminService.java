package com.udt.udt_ecommerce.core.service.admin;

import com.udt.udt_ecommerce.api.admin.dto.request.AdminAgencyCreateREQ;
import com.udt.udt_ecommerce.api.admin.dto.response.AdminAgencyRESP;
import com.udt.udt_ecommerce.api.agency.dto.AgencyRESP;
import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.api.customer.dto.response.CustomerRESP;
import com.udt.udt_ecommerce.api.transaction.response.TransactionRESP;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    AgencyRESP createAgency(AdminAgencyCreateREQ req);
    List<AgencyRESP> listAgencies();
    List<CustomerRESP> listCustomers();

    List<TransactionRESP> listTransactions();
    List<BillingRESP> listBilling();
}
