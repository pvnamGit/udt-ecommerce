package com.udt.udt_ecommerce.application.usecase.authentication;

import com.udt.udt_ecommerce.core.service.authentication.AuthenticationService;
import com.udt.udt_ecommerce.api.authentication.request.SignUpREQ;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase {
  private final AuthenticationService authenticationService;

  public SignUpUseCase(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @SneakyThrows
  public void signUp(SignUpREQ req) {
    authenticationService.registerAccount(req);
  }
}
