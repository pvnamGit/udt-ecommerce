package com.udt.udt_ecommerce.application.service.account;

import com.udt.udt_ecommerce.application.service.account.request.AccountCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.repository.account.AccountRepository;
import com.udt.udt_ecommerce.core.service.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Account createAccount(AccountCreateREQ dto) {
    Account account =
        Account.builder()
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .authority(dto.getAuthority())
            .build();
    accountRepository.saveAndFlush(account);
    return account;
  }
}
