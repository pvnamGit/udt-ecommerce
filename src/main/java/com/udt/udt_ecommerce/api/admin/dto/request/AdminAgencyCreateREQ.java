package com.udt.udt_ecommerce.api.admin.dto.request;

import com.udt.udt_ecommerce.core.entity.shared.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAgencyCreateREQ {
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")

    private String lastName;
    private String yearOfBirth;
    private String phone;
    private Gender gender;
}
