package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.Account;
import com.itschool.springapp.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public String getAccount(String accountDetails) {
        return "Account details: " + accountDetails;
    }

    @Override
    public String postAccount() {
        return "Account was created and confirmation email has been sent";
    }
}
