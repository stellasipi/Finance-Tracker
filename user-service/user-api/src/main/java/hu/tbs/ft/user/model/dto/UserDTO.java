package hu.tbs.ft.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
