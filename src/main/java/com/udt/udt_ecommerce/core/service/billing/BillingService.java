package com.udt.udt_ecommerce.core.service.billing;

import com.udt.udt_ecommerce.api.billing.response.BillingRESP;
import com.udt.udt_ecommerce.application.service.billing.request.BillingCreateREQ;
import com.udt.udt_ecommerce.core.entity.billing.Billing;

import java.util.List;

public interface BillingService {
    Billing createBilling(BillingCreateREQ transaction);
    List<BillingRESP> listBilling();
}
