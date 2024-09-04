package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.repository.AccountDAO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceDAOImpl implements AccountServiceDAO{

    private final AccountDAO accountDAO;

    public AccountServiceDAOImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    public void saveAccount(Account account) {
        accountDAO.saveAccount(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public String deleteAccount(Long id) {
        accountDAO.deleteAccount(id);
        return "Account deleted";
    }
}
