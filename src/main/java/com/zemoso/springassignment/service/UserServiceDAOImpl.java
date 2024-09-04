package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.Account;
import com.zemoso.springassignment.model.User;
import com.zemoso.springassignment.repository.UserDAO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceDAOImpl implements UserServiceDAO{

    UserDAO userDAO;

    PasswordEncoder passwordEncoder;

    UserServiceDAOImpl(UserDAO userDAO, PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
        this.userDAO=userDAO;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = userDAO.getUserById(user.getId());
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            userDAO.saveUser(existingUser);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userDAO.getUserById(id);

        List<Account> accounts = user.getAccounts();

        for(Account  account : accounts)
            account.setUser(null);

        userDAO.deleteUser(id);
    }
}
