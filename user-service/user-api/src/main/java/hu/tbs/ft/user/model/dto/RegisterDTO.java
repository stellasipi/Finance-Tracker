package hu.tbs.ft.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO extends ModifyUserDTO {

    @NotEmpty(message = "Password can't be null or empty")
    @NotBlank(message = "Password can't be null or whitespaces")
    @Size(min = 6, max = 20, message = "Password is not the correct length")
    private String password;
}
