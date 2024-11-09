package com.udt.udt_ecommerce.api.customer.dto.response;

import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.entity.shared.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomerRESP {
    private Long accountId;
    private String email;
    private Long customerId;
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String phone;
    private Gender gender;
    public CustomerRESP(Customer customer, Account account) {
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.customerId = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.yearOfBirth = customer.getYearOfBirth();
        this.phone = customer.getPhone();
        this.gender = customer.getGender();
    }

}
