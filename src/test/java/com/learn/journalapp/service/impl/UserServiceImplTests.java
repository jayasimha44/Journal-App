package com.learn.journalapp.service.impl;

import com.learn.journalapp.entity.User;
import com.learn.journalapp.repository.UserRepository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceImplTests {

//    @BeforeEach
//    @BeforeAll
//    @AfterEach
//    @AfterAll


    @Autowired
    private UserRepository userRepository;


    //    @ParameterizedTest
//    @CsvSource({
//            "amit,ranju@143",
//            "sai,mouni@143"
//    })
    @Disabled
    @Test
    public void testSaveUser() {
        User user = new User();
        assertNotNull(userRepository.save(user));
    }


    //    @Disabled
//    @Test
    @ParameterizedTest
    @ValueSource(strings = {    // ints = {}
//            "ram",
            "krishna",
//            "jayasimha",
//            "amit"
    })
//    @CsvSource({
//            "ram",
//            "krishna",
//            "jayasimha",
//            "amit"
//    })
    public void testGetUser(String username) {
        assertNotNull(userRepository.findByUsername(username));
//        assertEquals("amit", userRepository.findByUsername("amit").getUsername());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "2,4,6",
            "12,12,24",
//            "3,3,9"
    })
    public void testAdditon(int a, int b, int expected) {
        assertEquals(expected, (a + b));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
//            "ram",
//            "jayasimha"
    })
    public void testDeleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
        assertNull(user);
    }
}
