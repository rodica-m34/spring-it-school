package com.itschool.springapp.controller;

import com.itschool.springapp.service.Account;
import com.itschool.springapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{lastName}")
    public Account getAccount (@PathVariable String lastName){
        return accountService.getAccount(lastName);
    }

    @GetMapping("/get-all-accounts")
    public List<Account> getAccounts (){
        return accountService.getAccounts();
    }

    @PostMapping
    public Account postAccount (@RequestBody Account account){
        return accountService.createAccount(account);
    }
}
