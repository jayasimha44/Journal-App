package com.learn.journalapp.service;

import com.learn.journalapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    void createAdmin(User user);

    Optional<User> getUser(String username);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(String username);

}
