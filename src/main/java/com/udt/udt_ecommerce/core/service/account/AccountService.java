package com.udt.udt_ecommerce.core.service.account;

import com.udt.udt_ecommerce.application.service.account.request.AccountCreateREQ;
import com.udt.udt_ecommerce.core.entity.account.Account;

public interface AccountService {
    Account createAccount(AccountCreateREQ dto);
}
