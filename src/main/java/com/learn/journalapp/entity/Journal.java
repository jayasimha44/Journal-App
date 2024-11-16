package com.learn.journalapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "journals")
public class Journal {

    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime date;
}
