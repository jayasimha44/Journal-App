package com.learn.journalapp.controller;

import com.learn.journalapp.api.response.WeatherResponse;
import com.learn.journalapp.entity.User;
import com.learn.journalapp.repository.UserRepository;
import com.learn.journalapp.service.UserService;
import com.learn.journalapp.service.impl.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WeatherServiceImpl weatherService;

    @GetMapping("/details")
    public ResponseEntity<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUser(authentication.getName()).orElseThrow();
        return ResponseEntity.ok(user);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("User Updated Successfully!");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getName());
        return ResponseEntity.ok("User Deleted Successfully!");
    }

    @GetMapping("/greet/{city}")
    public ResponseEntity<String> greetUser(@PathVariable String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int feelslike = weatherService.getWeather(city).getCurrent().getFeelslike();
        return ResponseEntity.ok("Hello "+authentication.getName()+" Weather is do good, that feels like "+feelslike);
    }

    @GetMapping("weather/{city}")
    public ResponseEntity<WeatherResponse> getWeatherData(@PathVariable String city){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
           return ResponseEntity.ok(weatherService.getWeather(city));
    }
}
