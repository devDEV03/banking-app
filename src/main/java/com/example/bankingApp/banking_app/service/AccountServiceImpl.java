package com.example.bankingApp.banking_app.service;


import com.example.bankingApp.banking_app.accountMapper.AccountMapper;
import com.example.bankingApp.banking_app.dto.AccountDto;
import com.example.bankingApp.banking_app.entity.Account;
import com.example.bankingApp.banking_app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = AccountMapper.toAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toAccountdto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.toAccountdto(account);
    }

    @Override
    public AccountDto deposit(double amount, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        double totalBalance = account.getBalance()+amount;
        account.setBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toAccountdto(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }

        double remainingAmount = account.getBalance() - amount;
        account.setBalance(remainingAmount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toAccountdto(savedAccount);

    }

    @Override
    public AccountDto delete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.delete(account);
        return AccountMapper.toAccountdto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map((account) -> AccountMapper.toAccountdto(account)).collect(Collectors.toList());
    }
}
