package com.learn.journalapp.controller;

import com.learn.journalapp.dto.JournalDto;
import com.learn.journalapp.dto.ResponseDto;
import com.learn.journalapp.entity.Journal;
import com.learn.journalapp.entity.User;
import com.learn.journalapp.mapper.JournalMapper;
import com.learn.journalapp.service.JournalService;
import com.learn.journalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journals")
@RequiredArgsConstructor
public class JournalController {

    private static final Logger log = LoggerFactory.getLogger(JournalController.class);
    private final JournalService journalService;
    private final UserService userService;

//   private Map<Integer, Journal> journals = new HashMap<>();

    @PostMapping("")
    public ResponseEntity<ResponseDto> createJournal(@RequestBody Journal journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        journal.setDate(LocalDateTime.now());
        journalService.createJournal(journal, authentication.getName());
//        journals.put(journal.getId(), journal);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.CREATED, "Journal created successfully!"));
    }

    @GetMapping("all")
    public ResponseEntity<List<JournalDto>> getJournalsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.getUser(authentication.getName());
        return ResponseEntity.ok(user.get().getJournals().stream().map(JournalMapper::mapToJournalDto).collect(Collectors.toList()));
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Optional<JournalDto>> getJournalById(@PathVariable String id) {
        Optional<JournalDto> journalDto = journalService.getJournalById(id);
        if (journalDto.isPresent()) {
            return ResponseEntity.ok(journalDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteJournalById(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        journals.remove(id);
        journalService.deleteJournal(id, authentication.getName());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.NO_CONTENT, "Deleted Successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateJournal(@RequestBody Journal journal, @PathVariable String id) {
//        journals.put(id, journal);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUser(authentication.getName()).orElseThrow();
        journal.setDate(LocalDateTime.now());
        JournalDto journalDto1 = journalService.updateJournal(journal, id, authentication.getName());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.ACCEPTED, "Updated Successfully!"));
    }
}
