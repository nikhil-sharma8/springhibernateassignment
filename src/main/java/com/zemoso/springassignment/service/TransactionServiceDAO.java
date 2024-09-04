package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.Transaction;

import java.util.List;

public interface TransactionServiceDAO {
    List<Transaction> getAllTransactions();

    void saveTransaction(Integer amount, String type, Long accountId);

    Transaction getTransactionById(Long id);

    String deleteTransaction(Long id);
}
