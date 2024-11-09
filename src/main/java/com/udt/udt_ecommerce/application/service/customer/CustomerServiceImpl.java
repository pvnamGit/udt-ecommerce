package com.udt.udt_ecommerce.application.service.customer;

import com.udt.udt_ecommerce.application.service.customer.request.CustomerCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.repository.customer.CustomerRepository;
import com.udt.udt_ecommerce.core.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  @Override
  public Customer createCustomer(CustomerCreateREQ dto, Account account) {
    Customer customer =
        Customer.builder()
            .account(account)
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .gender(dto.getGender())
            .phone(dto.getPhone())
            .yearOfBirth(dto.getYearOfBirth())
            .build();
    customerRepository.saveAndFlush(customer);
    return customer;
  }

  @Override
  public List<Customer> listCustomers() {
    return customerRepository.findAll();
  }
}
