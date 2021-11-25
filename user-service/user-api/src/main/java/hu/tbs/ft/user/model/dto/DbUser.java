package hu.tbs.ft.user.model.dto;

import lombok.Data;

@Data
public class DbUser extends UserDTO {
    private String password;
}
