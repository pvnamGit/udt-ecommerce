package com.udt.udt_ecommerce.application.usecase.authentication;

import com.udt.udt_ecommerce.core.service.authentication.AuthenticationService;
import com.udt.udt_ecommerce.api.authentication.request.SignInREQ;
import com.udt.udt_ecommerce.api.authentication.response.AuthenticationRESP;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInUseCase {
  private final AuthenticationService authenticationService;

  @SneakyThrows
  public AuthenticationRESP signIn(SignInREQ req) {
    return authenticationService.signIn(req);
  }
}
