package com.udt.udt_ecommerce.application.service.account.request;

import com.udt.udt_ecommerce.core.entity.account.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountCreateREQ {
    private String email;
    private String password;
    private Authority authority;
}
