package com.itschool.springapp.service;

import java.util.List;

public interface AccountService {
    Account getAccount (String lastName);
    Account createAccount (Account account);
    List<Account> getAccounts ();
}
