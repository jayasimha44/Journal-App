package com.learn.journalapp.controller;

import com.learn.journalapp.entity.User;
import com.learn.journalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    @GetMapping("/health")
    public String checkHealth() {
        return "Ok!";
    }

    @PostMapping("/newUser")
    public ResponseEntity<String> newUser(@RequestBody User user) {
        List<User> userList = userService.getAllUsers();
        for (User user1 : userList) {
            if (user1.getId().equals(user.getId()) || user1.getUsername().equals(user.getUsername())) {
                log.trace("TRACING the LOG!");
                log.debug("DEBUGGING THE LOG!");
                log.info("INFO OF LOG!");
                log.warn("WARNING THE LOG!");
                log.error("ERROR THE LOG!");
                return ResponseEntity.unprocessableEntity().body("User Already Exists with this Id or Username! please try with different username!");
            }
        }
        userService.saveUser(user);
        return ResponseEntity.ok("User Created successfully!");
    }
}
