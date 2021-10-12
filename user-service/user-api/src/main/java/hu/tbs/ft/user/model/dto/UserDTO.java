package hu.tbs.ft.user.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private List<RoleDTO> roles;
    private List<String> reminders; //TODO ReminderDTO
}
