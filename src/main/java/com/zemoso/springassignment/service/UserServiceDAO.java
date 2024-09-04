package com.zemoso.springassignment.service;

import com.zemoso.springassignment.model.User;

import java.util.List;

public interface UserServiceDAO {

    public List<User> getAllUser();

    public void saveUser(User user);

    public void updateUser(User user);

    public User getUserById(Long id);

    public void deleteUser(Long id);
}
