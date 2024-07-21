package com.example.bankingApp.banking_app.accountMapper;

import com.example.bankingApp.banking_app.dto.AccountDto;
import com.example.bankingApp.banking_app.entity.Account;

public class AccountMapper {
    public static Account toAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getAccountId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto toAccountdto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getAccountId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
