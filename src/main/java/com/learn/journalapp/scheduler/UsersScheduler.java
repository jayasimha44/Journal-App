package com.learn.journalapp.scheduler;

import com.learn.journalapp.entity.User;
import com.learn.journalapp.repository.UserRepository;
import com.learn.journalapp.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersScheduler {

    private final EmailServiceImpl emailService;
    private final UserRepository userRepository;

    private List<String> usernames;

//    @Scheduled(cron = "0 * * ? * *")
@Scheduled(cron = "0 * 9 ? * MON")
    public void fetchAndSendUsersList() {
        usernames = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            usernames.add(user.getUsername());
        }
        emailService.sendMail("figaj11952@lineacr.com", "Users", usernames.toString());
    }
}
