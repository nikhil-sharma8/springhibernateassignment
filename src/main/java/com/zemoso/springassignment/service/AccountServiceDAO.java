package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.Account;

import java.util.List;

public interface AccountServiceDAO {
    List<Account> getAllAccounts();

    void saveAccount(Account account);

    Account getAccountById(Long id);

    String deleteAccount(Long id);
}
