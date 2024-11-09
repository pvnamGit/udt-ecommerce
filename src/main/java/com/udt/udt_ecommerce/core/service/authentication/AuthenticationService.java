package com.udt.udt_ecommerce.core.service.authentication;

import com.udt.udt_ecommerce.api.authentication.request.SignInREQ;
import com.udt.udt_ecommerce.api.authentication.request.SignUpREQ;
import com.udt.udt_ecommerce.api.authentication.response.AuthenticationRESP;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    void registerAccount(SignUpREQ signUpRequest) throws ConstraintViolationException;
    AuthenticationRESP signIn(SignInREQ req);
}
