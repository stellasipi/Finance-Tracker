package hu.tbs.ft.report;

import hu.tbs.ft.user.model.dto.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReportDTO {
    private UUID id;
    private UserDTO user;
    private String name;
    private LocalDateTime createDate;
    private String type;
    private String description;
}
