package com.learn.journalapp.service.impl;

import com.learn.journalapp.dto.JournalDto;
import com.learn.journalapp.entity.Journal;
import com.learn.journalapp.entity.User;
import com.learn.journalapp.exception.ResourceNotFoundException;
import com.learn.journalapp.mapper.JournalMapper;
import com.learn.journalapp.repository.JournalRepository;
import com.learn.journalapp.repository.UserRepository;
import com.learn.journalapp.service.JournalService;
import com.learn.journalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createJournal(Journal journal, String username) {
        try {
            User user = userService.getUser(username).orElseThrow();
            Journal savedJournal = journalRepository.save(journal);
            user.getJournals().add(savedJournal);
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }

    }

    @Override
    public List<JournalDto> getAllJournals() {
        List<Journal> journalList = journalRepository.findAll();
        return journalList.stream().map(JournalMapper::mapToJournalDto).toList();
    }

    @Override
    public Optional<JournalDto> getJournalById(String id) {
        Journal journal = journalRepository.findById(id).orElseThrow();
        return Optional.ofNullable(JournalMapper.mapToJournalDto(journal));
    }

    @Transactional
    @Override
    public JournalDto updateJournal(Journal journal, String id, String username) {
        try {
            JournalDto journalDto1 = JournalMapper.mapToJournalDto(journalRepository.findById(id).orElseThrow());
            User user = userService.getUser(username).orElseThrow();
            journalDto1.setTitle(journal.getTitle());
            journalDto1.setDescription(journal.getDescription());
            Journal journal1 = user.getJournals().stream().filter(u -> u.getId().equals(journalDto1.getId())).findFirst().orElseThrow();
            journal1.setTitle(journalDto1.getTitle());
            journal1.setDescription(journalDto1.getDescription());
            journal1.setDate(journalDto1.getDate());
            journalRepository.save(JournalMapper.mapToJournal(journalDto1));
            userRepository.save(user);
            return journalDto1;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Journal Not found!");
        }
    }

    @Transactional
    @Override
    public String deleteJournal(String id, String username) {
        try {
            User user = userService.getUser(username).orElseThrow();
            user.getJournals().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalRepository.deleteById(id);
            return "Deleted Successfully!";
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}