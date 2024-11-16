package com.learn.journalapp.service;

import com.learn.journalapp.dto.JournalDto;
import com.learn.journalapp.entity.Journal;

import java.util.List;
import java.util.Optional;

public interface JournalService {
    void createJournal(Journal journal, String username);

    List<JournalDto> getAllJournals();

    Optional<JournalDto> getJournalById(String id);

    JournalDto updateJournal(Journal journal, String id, String username);

    String deleteJournal(String id, String username);
}
