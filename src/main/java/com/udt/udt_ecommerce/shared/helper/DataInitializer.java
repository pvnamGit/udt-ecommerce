package com.udt.udt_ecommerce.shared.helper;

import com.udt.udt_ecommerce.core.entity.account.Account;
import com.udt.udt_ecommerce.core.entity.account.Authority;
import com.udt.udt_ecommerce.core.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
  @Value("${data.initialize}")
  private Boolean initialize;

  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private AccountRepository accountRepository;

  @Override
  public void run(String... args) throws Exception {
    if (!initialize) return;
    final String ADMIN_EMAIL = "admin@udt.com";
    final String ADMIN_PASSWORD = "admin@udt";

    Account existingAdmin = accountRepository.findByEmail(ADMIN_EMAIL).orElse(null);
    if (existingAdmin != null) return;

    Account admin =
        Account.builder()
            .email(ADMIN_EMAIL)
            .password(passwordEncoder.encode(ADMIN_PASSWORD))
            .authority(Authority.ADMIN)
            .build();
    accountRepository.save(admin);
  }
}
