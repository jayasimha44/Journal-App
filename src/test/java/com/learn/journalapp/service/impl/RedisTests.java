package com.learn.journalapp.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
   public void testSendMail() {
//        redisTemplate.opsForValue().set("email", "jayasimha@email.com");

        String email = redisTemplate.opsForValue().get("email");
        String name = redisTemplate.opsForValue().get("name");
        if (name != null && email != null) {
            assertNotNull(name);
            assertNotNull(email);
        }
    }
}
