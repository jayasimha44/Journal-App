package com.learn.journalapp.mapper;

import com.learn.journalapp.dto.JournalDto;
import com.learn.journalapp.entity.Journal;

public class JournalMapper {
    public static Journal mapToJournal(JournalDto journalDto) {
        return Journal.builder()
                .id(journalDto.getId())
                .title(journalDto.getTitle())
                .description(journalDto.getDescription())
                .date(journalDto.getDate())
                .build();
//       Journal journal1 = new Journal();
//       journal1.setId(journalDto.getId());
//       journal1.setTitle(journalDto.getTitle());
//       journal1.setDescription(journalDto.getDescription());
//       return journal1;
    }

    public static JournalDto mapToJournalDto(Journal journal) {
        return JournalDto.builder()
                .id(journal.getId())
                .title(journal.getTitle())
                .description(journal.getDescription())
                .date(journal.getDate())
                .build();
//        JournalDto journalDto = new JournalDto();
//        journalDto.setId(journal.getId());
//        journalDto.setTitle(journal.getTitle());
//        journalDto.setDescription(journal.getDescription());
//        return journalDto;
    }
}
