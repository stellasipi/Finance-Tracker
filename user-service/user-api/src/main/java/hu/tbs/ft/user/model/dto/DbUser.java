package hu.tbs.ft.user.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DbUser extends UserDTO {
    private String password;
}
