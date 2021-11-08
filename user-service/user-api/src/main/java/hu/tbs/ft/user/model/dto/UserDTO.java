package hu.tbs.ft.user.model.dto;

import hu.tbs.ft.planning.ReminderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String role;
}
