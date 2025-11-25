package com.itschool.springapp.service.impl;

import com.itschool.springapp.service.Account;
import com.itschool.springapp.service.AccountService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account getAccount(String lastName) {
        for (Account account : accounts){
            if (account.lastName().equals(lastName)){
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public Account createAccount(Account account) {
        if (account.firstName()==(null) || account.lastName()==null){
            return null;
        }
        if (account.firstName().isEmpty() || account.lastName().isEmpty()){
            return null;
        }
        if (account.firstName().length()<2 || account.lastName().length()<2){
            return null;
        }
        EmailValidator validator = EmailValidator.getInstance();
        boolean isAddressValid = validator.isValid(account.email());
        if (!isAddressValid){
            return null;
        }

        //generare id
        int idForNewAccount = 0;
        if (!accounts.isEmpty()){
            idForNewAccount = accounts.getLast().id() + 1;
        }
        Account accountToBeAdded = new Account(idForNewAccount,
                account.firstName(),
                account.lastName(),
                account.email(),
                LocalDateTime.now());
        accounts.add(accountToBeAdded);
        System.out.println("Account was created and confirmation email has been sent");
        return accountToBeAdded;
    }
}
