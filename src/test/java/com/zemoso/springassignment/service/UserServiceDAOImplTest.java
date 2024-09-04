package com.zemoso.springassignment.service;


import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.User;
import com.zemoso.springassignment.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceDAOImplTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceDAOImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUser() {
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userDAO.getAllUser()).thenReturn(mockUsers);

        List<User> result = userService.getAllUser();

        assertEquals(2, result.size());
        verify(userDAO, times(1)).getAllUser();
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPassword("some");
        when(passwordEncoder.encode("some")).thenReturn("encodedPassword");

        userService.saveUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(userDAO, times(1)).saveUser(user);
        verify(passwordEncoder, times(1)).encode("some");
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1L);
        User user = new User();
        user.setId(1L);
        user.setUsername("Nikhil");
        user.setPassword("sharma");
        user.setEmail("n@gmail.com");

        when(userDAO.getUserById(1L)).thenReturn(existingUser);

        userService.updateUser(user);

        assertEquals("Nikhil", existingUser.getUsername());
        assertEquals("sharma", existingUser.getPassword());
        assertEquals("n@gmail.com", existingUser.getEmail());
        verify(userDAO, times(1)).saveUser(existingUser);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(userDAO.getUserById(1L)).thenReturn(user);

        User result = userService.getUserById(1L);

        assertEquals(user, result);
        verify(userDAO, times(1)).getUserById(1L);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        User user = new User();
        Account account = new Account();
        account.setUser(user);
        user.setAccounts(Collections.singletonList(account));

        when(userDAO.getUserById(1L)).thenReturn(user);

        userService.deleteUser(1L);

        assertNull(account.getUser());
        verify(userDAO, times(1)).deleteUser(1L);
    }
}
