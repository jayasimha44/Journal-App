package com.learn.journalapp.service.impl;

import com.learn.journalapp.entity.User;
import com.learn.journalapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@SpringBootTest
public class UserDetailsServiceImplTests {

    //    @Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    //    @MockBean
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(User
                        .builder()
                        .username("ram")
                        .password("dsfkh534252kjhsfkhlsaj234.242525242452#3234252")
                        .roles(new ArrayList<>())
                        .build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        assertNotNull(user);
    }
}
