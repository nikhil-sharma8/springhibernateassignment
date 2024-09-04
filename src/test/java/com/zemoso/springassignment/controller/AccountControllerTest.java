package com.zemoso.springassignment.controller;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.service.AccountServiceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private AccountServiceDAO accountServiceDAO;

    @Mock
    private Model model;

    @InjectMocks
    AccountController accountController;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts(){
        List<Account> accounts= Arrays.asList(new Account(), new Account());
        when(accountServiceDAO.getAllAccounts()).thenReturn(accounts);

        String viewName = accountController.getAllAccounts(model);

        assertEquals("account-list",viewName);

        verify(model, times(1)).addAttribute("accounts",accounts);
    }

    @Test
    public void testCreateAccountForm(){
        String viewName = accountController.createAccount();
        assertEquals("account-form", viewName);
    }

    @Test
    public void testCreateAccount(){
        Account account = new Account();

        String viewName = accountController.createAccount(account);

        assertEquals("redirect:/account/accounts", viewName);
    }
}
