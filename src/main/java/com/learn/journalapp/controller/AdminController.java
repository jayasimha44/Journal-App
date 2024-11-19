package com.learn.journalapp.controller;

import com.learn.journalapp.cache.AppCache;
import com.learn.journalapp.entity.User;
import com.learn.journalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("new-admin")
    public ResponseEntity<String> newUser(@RequestBody User user) {
        List<User> userList = userService.getAllUsers();
        for (User user1 : userList) {
            if (user1.getId().equals(user.getId()) || user1.getUsername().equals(user.getUsername())) {
                return ResponseEntity.unprocessableEntity().body("User Already Exists with this Id or Username! please try with different username!");
            }
        }
        userService.createAdmin(user);
        return ResponseEntity.ok("Admin Created successfully!");
    }

    @GetMapping("clear-app-cache")
    public void clearCache() {
        appCache.init();
    }
}
