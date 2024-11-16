package com.learn.journalapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalDto {
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
}
