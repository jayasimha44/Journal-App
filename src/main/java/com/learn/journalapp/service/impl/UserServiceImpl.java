package com.learn.journalapp.service.impl;

import com.learn.journalapp.entity.User;
import com.learn.journalapp.repository.UserRepository;
import com.learn.journalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }

    @Override
    public void createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> userList = userRepository.findAll();
            return userList.stream().toList();
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userToUpdate = userRepository.findByUsername(authentication.getName());
        if (userToUpdate != null) {
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setJournals(user.getJournals());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteUser(String username) {
        User userToDelete = userRepository.findByUsername(username);
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
        }
    }
}
