package com.zemoso.springassignment.service;


import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.Transaction;
import com.zemoso.springassignment.model.utils.enums.TType;
import com.zemoso.springassignment.repository.AccountDAO;
import com.zemoso.springassignment.repository.TransactionDAO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionServiceDAOImpl implements TransactionServiceDAO {

    private final TransactionDAO transactionDAO;

    private final AccountDAO accountDAO;

    public TransactionServiceDAOImpl(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public void saveTransaction(Integer amount, String type, Long accountId) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(TType.valueOf(type));
        Account account = accountDAO.getAccountById(accountId);

        if (account != null) {

            if(type.equals("DEBIT"))
                account.setBalance(account.getBalance() - amount);
            else
                account.setBalance(account.getBalance() + amount);
            transaction.setAccount(account);
            transactionDAO.saveTransaction(transaction);
        }
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionDAO.getTransactionById(id);
    }

    @Override
    public String deleteTransaction(Long id) {
        transactionDAO.deleteTransaction(id);
       return "Transaction deleted";
    }

}
