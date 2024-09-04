package com.zemoso.springassignment.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.Transaction;
import com.zemoso.springassignment.repository.AccountDAO;
import com.zemoso.springassignment.repository.TransactionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TransactionServiceDAOImplTest {

    @Mock
    private TransactionDAO transactionDAO;

    @Mock
    private AccountDAO accountDAO;

    @InjectMocks
    private TransactionServiceDAOImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> mockTransactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionDAO.getAllTransactions()).thenReturn(mockTransactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(2, result.size());
        verify(transactionDAO, times(1)).getAllTransactions();
    }

    @Test
    void testSaveTransaction_Credit() {
        Account account = new Account();
        account.setBalance(1000.0);
        when(accountDAO.getAccountById(1L)).thenReturn(account);

        transactionService.saveTransaction(200, "CREDIT", 1L);

        assertEquals(1200, account.getBalance());
        verify(transactionDAO, times(1)).saveTransaction(any(Transaction.class));
    }

    @Test
    void testSaveTransaction_Debit() {
        Account account = new Account();
        account.setBalance(1000.0);
        when(accountDAO.getAccountById(1L)).thenReturn(account);

        transactionService.saveTransaction(200, "DEBIT", 1L);

        assertEquals(800, account.getBalance());
        verify(transactionDAO, times(1)).saveTransaction(any(Transaction.class));
    }

    @Test
    void testSaveTransaction_AccountNotFound() {
        when(accountDAO.getAccountById(1L)).thenReturn(null);

        transactionService.saveTransaction(200, "CREDIT", 1L);

        verify(transactionDAO, never()).saveTransaction(any(Transaction.class));
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = new Transaction();
        when(transactionDAO.getTransactionById(1L)).thenReturn(transaction);

        Transaction result = transactionService.getTransactionById(1L);

        assertEquals(transaction, result);
        verify(transactionDAO, times(1)).getTransactionById(1L);
    }

    @Test
    void testDeleteTransaction() {
        Long transactionId = 1L;

        String result = transactionService.deleteTransaction(transactionId);

        assertEquals("Transaction deleted", result);
        verify(transactionDAO, times(1)).deleteTransaction(transactionId);
    }
}
