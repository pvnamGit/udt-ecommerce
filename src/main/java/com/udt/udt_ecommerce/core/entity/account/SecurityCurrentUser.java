package com.udt.udt_ecommerce.core.entity.account;

import com.udt.udt_ecommerce.core.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityCurrentUser {

  private final AccountRepository accountRepository;

  public SecurityAccountDetails getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SecurityAccountDetails account = (SecurityAccountDetails) authentication.getPrincipal();
    if (account == null) return null;
    return SecurityAccountDetails.buildCurrentUser(account);
  }
}
