package com.udt.udt_ecommerce.application.service.customer.request;

import com.udt.udt_ecommerce.core.entity.shared.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerCreateREQ {
  private String firstName;
  private String lastName;
  private String yearOfBirth;
  private String phone;
  private Gender gender;
}