package hu.tbs.ft.planning;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RepetitionDTO {
    private UUID id;
    private String name;
    private LocalDateTime createdDate;
    private String recurrence;
    private String description;
}
