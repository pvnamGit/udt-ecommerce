package com.udt.udt_ecommerce.core.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityAccountDetails implements UserDetails {
  private static final long serialVersionUID = 1L;
  @Getter private String email;

  @JsonIgnore private String password;
  @Getter private Long accountId;
  @Getter private Authority authority;
  @Getter private Long agencyId;
  @Getter private Long customerId;

  private Collection<? extends GrantedAuthority> authorities;

  public static SecurityAccountDetails build(Account account) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    switch (account.getAuthority()) {
      case ADMIN:
        authorities.add(new SimpleGrantedAuthority(Authority.ADMIN.name()));
        break;
      case CUSTOMER:
        authorities.add(new SimpleGrantedAuthority(Authority.CUSTOMER.name()));
        break;
      case AGENCY:
        authorities.add(new SimpleGrantedAuthority(Authority.AGENCY.name()));
        break;
    }
    var email = account.getEmail();
    var password = account.getPassword();
    var accountId = account.getId();
    var authority = account.getAuthority();
    var agencyId = account.getAgency() != null ? account.getAgency().getId() : null;
    var customerId = account.getCustomer() != null ? account.getCustomer().getId() : null;
    return new SecurityAccountDetails(
        email, password, accountId, authority, agencyId, customerId, authorities);
  }

  public static SecurityAccountDetails buildCurrentUser(SecurityAccountDetails accountDetail) {
    return SecurityAccountDetails.builder()
        .accountId(accountDetail.accountId)
        .email(accountDetail.getEmail())
        .authority(accountDetail.getAuthority())
        .agencyId(accountDetail.agencyId)
        .customerId(accountDetail.customerId)
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
