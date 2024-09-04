package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUser();

    public void saveUser(User user);

    public User getUserById(Long id);

    public void deleteUser(Long id);
}
