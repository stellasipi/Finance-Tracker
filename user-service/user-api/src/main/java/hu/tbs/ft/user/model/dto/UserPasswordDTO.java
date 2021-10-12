package hu.tbs.ft.user.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserPasswordDTO {
    private UUID id;
    private String username;
    private String oldPassword;
    private String newPassword;
}
