package com.udt.udt_ecommerce.api.authentication;

import com.udt.udt_ecommerce.api.authentication.request.SignInREQ;
import com.udt.udt_ecommerce.api.authentication.request.SignUpREQ;
import com.udt.udt_ecommerce.api.authentication.response.AuthenticationRESP;
import com.udt.udt_ecommerce.api.shared.BaseEntityResponse;
import com.udt.udt_ecommerce.application.usecase.authentication.SignInUseCase;
import com.udt.udt_ecommerce.application.usecase.authentication.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.prefix}/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final SignUpUseCase signUpUseCase;
  private final SignInUseCase signInUseCase;

  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public BaseEntityResponse signUp(@RequestBody @Valid SignUpREQ req) {
    signUpUseCase.signUp(req);
    return BaseEntityResponse.success();
  }

  @PostMapping("/sign-in")
  @ResponseStatus(HttpStatus.OK)
  public BaseEntityResponse signIn(@RequestBody @Valid SignInREQ req) {
    AuthenticationRESP response = signInUseCase.signIn(req);
    return BaseEntityResponse.success(response);
  }
}
