package com.udt.udt_ecommerce.api.authentication.response;

import com.udt.udt_ecommerce.core.entity.account.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRESP {
    private String token;
    private Authority authority;
}
