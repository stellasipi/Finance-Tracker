package hu.tbs.ft.planning;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReminderDTO {
    private UUID id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    private String description;
    private RepetitionDTO repetition;
}
