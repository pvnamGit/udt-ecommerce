package com.udt.udt_ecommerce.core.service.customer;

import com.udt.udt_ecommerce.application.service.customer.request.CustomerCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.customer.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerCreateREQ dto, Account account);
    List<Customer> listCustomers();
}
