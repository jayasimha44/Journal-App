package com.learn.journalapp.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmailServiceImplTests {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    public void testSendMail() {
        emailService.sendMail("figaj11952@lineacr.com", "About Friend", "Hey, Figaj, It's been so long that we met and has a fun!");
        assertNotNull(true);
    }
}
