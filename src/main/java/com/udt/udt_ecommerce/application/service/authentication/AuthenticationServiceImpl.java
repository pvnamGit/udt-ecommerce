package com.udt.udt_ecommerce.application.service.authentication;

import com.udt.udt_ecommerce.api.authentication.request.SignInREQ;
import com.udt.udt_ecommerce.api.authentication.request.SignUpREQ;
import com.udt.udt_ecommerce.api.authentication.response.AuthenticationRESP;
import com.udt.udt_ecommerce.application.service.account.request.AccountCreateREQ;
import com.udt.udt_ecommerce.application.service.customer.request.CustomerCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.account.Authority;
import com.udt.udt_ecommerce.core.entity.account.SecurityAccountDetails;
import com.udt.udt_ecommerce.core.entity.customer.Customer;
import com.udt.udt_ecommerce.core.repository.account.AccountRepository;
import com.udt.udt_ecommerce.core.repository.customer.CustomerRepository;
import com.udt.udt_ecommerce.core.service.account.AccountService;
import com.udt.udt_ecommerce.core.service.authentication.AuthenticationService;
import com.udt.udt_ecommerce.core.service.customer.CustomerService;
import com.udt.udt_ecommerce.core.service.jwt.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final AccountRepository accountRepository;
  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final AccountService accountService;
  private final CustomerService customerService;

  @Override
  @Transactional
  @SneakyThrows
  public void registerAccount(SignUpREQ signUpRequest) throws ConstraintViolationException {
    try {
      Account account =
          accountService.createAccount(
              AccountCreateREQ.builder()
                  .email(signUpRequest.getEmail())
                  .password(signUpRequest.getPassword())
                  .authority(Authority.CUSTOMER)
                  .build());

      Customer customer =
          customerService.createCustomer(
              CustomerCreateREQ.builder()
                  .firstName(signUpRequest.getFirstName())
                  .lastName(signUpRequest.getLastName())
                  .phone(signUpRequest.getPhone())
                  .gender(signUpRequest.getGender())
                  .yearOfBirth(signUpRequest.getYearOfBirth())
                  .build(),
              account);

      customerRepository.save(customer);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityViolationException("Email is already registered");
    } catch (Exception exception) {
      throw new Exception("Can't create account");
    }
  }

  @Override
  public AuthenticationRESP signIn(SignInREQ req) {
    Account account =
        accountRepository
            .findByEmail(req.getEmail())
            .orElseThrow(() -> new EntityNotFoundException("Email not found"));

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException(e.getMessage());
    }

    SecurityAccountDetails accountDetails = SecurityAccountDetails.build(account);
    String token = jwtService.generateToken(accountDetails);
    return AuthenticationRESP.builder().token(token).authority(account.getAuthority()).build();
  }
}
