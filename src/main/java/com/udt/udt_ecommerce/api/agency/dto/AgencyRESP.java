package com.udt.udt_ecommerce.api.agency.dto;

import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.agency.Agency;
import com.udt.udt_ecommerce.core.entity.shared.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AgencyRESP {
    private Long accountId;
    private String email;
    private Long agencyId;
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String phone;
    private Gender gender;

    public AgencyRESP(Agency agency, Account account) {
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.agencyId = agency.getId();
        this.firstName = agency.getFirstName();
        this.lastName = agency.getLastName();
        this.yearOfBirth = agency.getYearOfBirth();
        this.phone = agency.getPhone();
        this.gender = agency.getGender();
    }
}
