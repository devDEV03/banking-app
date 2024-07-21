package com.example.bankingApp.banking_app.service;


import com.example.bankingApp.banking_app.dto.AccountDto;
import com.example.bankingApp.banking_app.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public interface AccountService {
    public AccountDto saveAccount(AccountDto accountDto);

    public AccountDto getAccountById(Long id);

    AccountDto deposit(double amount, Long id);

    AccountDto withdraw(Long id, double amount);

    AccountDto delete(Long id);

    List<AccountDto> getAllAccounts();
}
