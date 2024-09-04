package com.zemoso.springassignment.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.zemoso.springassignment.model.Transaction;
import com.zemoso.springassignment.service.TransactionServiceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
class TransactionControllerTest {
    @Mock
    private TransactionServiceDAO transactionServiceDAO;

    @Mock
    private Model model;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionServiceDAO.getAllTransactions()).thenReturn(transactions);

        String viewName = transactionController.getAllTransactions(model);

        assertEquals("transaction-list", viewName);
        verify(model, times(1)).addAttribute("transactions", transactions);
    }

    @Test
    void testCreateTransactionForm() {
        String viewName = transactionController.createTransaction();

        assertEquals("transaction-form", viewName);
    }

    @Test
    void testCreateTransaction() {
        String viewName = transactionController.createTransaction(100, "DEPOSIT", 1L);

        assertEquals("redirect:/transaction/transactions", viewName);
        verify(transactionServiceDAO, times(1)).saveTransaction(100, "DEPOSIT", 1L);
    }
}
