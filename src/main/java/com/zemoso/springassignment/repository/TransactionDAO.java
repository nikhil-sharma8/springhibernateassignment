package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    List<Transaction> getAllTransactions();

    void saveTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);

    void deleteTransaction(Long id);
}
