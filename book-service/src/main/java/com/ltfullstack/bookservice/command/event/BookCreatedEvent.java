package com.ltfullstack.bookservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
