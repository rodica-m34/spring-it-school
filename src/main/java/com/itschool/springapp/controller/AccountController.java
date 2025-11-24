package com.itschool.springapp.controller;

import com.itschool.springapp.service.Account;
import com.itschool.springapp.service.AccountService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class AccountController {
    private final AccountService accountService;
    private final ArrayList <Account> accounts = new ArrayList<>();

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{lastName}")
    public String getAccount (@PathVariable String lastName){
        for (Account account : accounts){
            if (account.lastName().equals(lastName)){
                return accountService.getAccount(String.valueOf(account));
            }
        }
        return "The user does not exist";
    }

    @GetMapping("/getAllAccounts")
    public ArrayList<Account> getAccounts (){
        return accounts;
    }

    @PostMapping("/addAccount")
    public String postAccount (@RequestBody Account account){
        if (account.firstName()==(null) || account.lastName()==null){
            return "Null Name is not allowed for Account creation!";
        }
        if (account.firstName().isEmpty() || account.lastName().isEmpty()){
            return "Fields should not be empty!";
        }
        if (account.firstName().length()<2 || account.lastName().length()<2){
            return "Invalid name length!";
        }
        EmailValidator validator = EmailValidator.getInstance();
        boolean isAddressValid = validator.isValid(account.email());
        if (!isAddressValid){
            return "Invalid email address!";
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
        return accountService.postAccount();
    }
}
