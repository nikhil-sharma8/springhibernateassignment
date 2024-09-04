package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.repository.AccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceDAOImplTest {
    @Mock
    private AccountDAO accountDAO;

    @InjectMocks
    private AccountServiceDAOImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> mockAccounts = Arrays.asList(new Account(), new Account());
        when(accountDAO.getAllAccounts()).thenReturn(mockAccounts);

        List<Account> result = accountService.getAllAccounts();

        assertEquals(2, result.size());
        verify(accountDAO, times(1)).getAllAccounts();
    }

    @Test
    public void testSaveAccount() {
        Account account = new Account();

        accountService.saveAccount(account);

        verify(accountDAO, times(1)).saveAccount(account);
    }

    @Test
    public void testGetAccountById() {
        Account account = new Account();
        when(accountDAO.getAccountById(1L)).thenReturn(account);

        Account result = accountService.getAccountById(1L);

        assertEquals(account, result);
        verify(accountDAO, times(1)).getAccountById(1L);
    }

    @Test
    public void testDeleteAccount() {
        Long accountId = 1L;

        String result = accountService.deleteAccount(accountId);

        assertEquals("Account deleted", result);
        verify(accountDAO, times(1)).deleteAccount(accountId);
    }
}
