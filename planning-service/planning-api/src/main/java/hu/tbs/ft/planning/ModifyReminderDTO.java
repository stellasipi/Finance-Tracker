package hu.tbs.ft.planning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyReminderDTO {
    @NotEmpty(message = "Name can't be null or empty")
    @NotBlank(message = "Name can't be null or whitespaces")
    private String name;

    private LocalDateTime deadline;

    private String description;
}
