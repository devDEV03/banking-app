package com.example.bankingApp.banking_app.controller;


import com.example.bankingApp.banking_app.accountMapper.AccountMapper;
import com.example.bankingApp.banking_app.dto.AccountDto;
import com.example.bankingApp.banking_app.entity.Account;
import com.example.bankingApp.banking_app.service.AccountService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/accounts")
public class BankingController {

    @Autowired
    private AccountService accountService;

//    @GetMapping("/hello")
//    public String hello(){
//        return "Welcome to Banking Application";
//    }

    @PostMapping
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.saveAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositInAccount(@PathVariable Long id, @RequestBody Map<String,Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(amount,id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawFromAccount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("withdraw");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.delete(id);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }


}
