package hu.tbs.ft.planning;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReminderDTO extends ModifyReminderDTO {
    private UUID id;
    private UUID userId;
    private LocalDateTime createdDate;
}
