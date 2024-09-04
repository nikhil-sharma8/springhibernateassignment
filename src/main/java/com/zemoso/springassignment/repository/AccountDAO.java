package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.Account;

import java.util.List;

public interface AccountDAO {

    public List<Account> getAllAccounts();

    public void saveAccount(Account account);

    public Account getAccountById(Long id);

    public void deleteAccount(Long id);
}
