package com.zemoso.springassignment.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.User;
import com.zemoso.springassignment.service.AccountServiceDAO;
import com.zemoso.springassignment.service.UserServiceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserServiceDAO userServiceDAO;

    @Mock
    private AccountServiceDAO accountServiceDAO;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        String viewName = userController.getUsers();

        assertEquals("demo", viewName);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userServiceDAO.getAllUser()).thenReturn(users);

        String viewName = userController.getAllUsers(model);

        assertEquals("user-list", viewName);
        verify(model, times(1)).addAttribute("users", users);
    }

    @Test
    public void testCreateUserForm() {
        String viewName = userController.createUser();

        assertEquals("user-form", viewName);
    }

    @Test
    public void testCreateUser() {
        User user = new User();

        String viewName = userController.createUser(user);

        assertEquals("redirect:/user/users", viewName);
        verify(userServiceDAO, times(1)).saveUser(user);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        String viewName = userController.deleteUser(userId);

        assertEquals("redirect:/user/users", viewName);
        verify(userServiceDAO, times(1)).deleteUser(userId);
    }

    @Test
    public void testEditUser() {
        Long userId = 1L;
        User user = new User();
        when(userServiceDAO.getUserById(userId)).thenReturn(user);

        String viewName = userController.editUser(userId, model);

        assertEquals("user-form", viewName);
        verify(model, times(1)).addAttribute("user", user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();

        String viewName = userController.updateUser(user);

        assertEquals("redirect:/user/users", viewName);
        verify(userServiceDAO, times(1)).updateUser(user);
    }

    @Test
    public void testLinkAccountToUser_ValidUserAndAccount() {
        Long userId = 1L;
        Long accountId = 1L;
        User user = new User();
        Account account = new Account();
        when(userServiceDAO.getUserById(userId)).thenReturn(user);
        when(accountServiceDAO.getAccountById(accountId)).thenReturn(account);

        String viewName = userController.linkAccountToUser(userId, accountId, model);

        assertEquals("redirect:/user/users", viewName);
        verify(accountServiceDAO, times(1)).saveAccount(account);
    }

    @Test
    public void testLinkAccountToUser_InvalidUserOrAccount() {
        Long userId = 1L;
        Long accountId = 1L;
        when(userServiceDAO.getUserById(userId)).thenReturn(null);
        when(accountServiceDAO.getAccountById(accountId)).thenReturn(null);

        // Act
        String viewName = userController.linkAccountToUser(userId, accountId, model);

        // Assert
        assertEquals("error-page", viewName);
        verify(model, times(1)).addAttribute("error", "Invalid user ID or account ID");
        verify(accountServiceDAO, never()).saveAccount(any(Account.class));
    }
}
