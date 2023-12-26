package com.workintech.S19d2.controller;

import com.workintech.S19d2.entity.Account;
import com.workintech.S19d2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountControlller {

    private AccountService accountService;

    @Autowired
    public AccountControlller(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/")
    public Account save(@RequestBody Account account){
        return accountService.save(account);
    }
    @GetMapping("/")
    public List<Account> findAll(){
        return accountService.findAll();
    }


}
