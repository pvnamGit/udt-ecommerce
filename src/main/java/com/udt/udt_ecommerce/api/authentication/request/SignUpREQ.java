package com.udt.udt_ecommerce.api.authentication.request;


import com.udt.udt_ecommerce.core.entity.shared.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SignUpREQ {
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotEmpty(message = "Confirm password is required")
    private String confirmPassword;

    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")

    private String lastName;
    private String yearOfBirth;
    private String phone;
    private Gender gender;

    @AssertTrue(message = "Passwords do not match")
    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword);
    }
}
