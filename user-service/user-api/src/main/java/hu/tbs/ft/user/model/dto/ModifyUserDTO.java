package hu.tbs.ft.user.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ModifyUserDTO {

    @NotEmpty(message = "Name can't be null or empty")
    @NotBlank(message = "Name can't be null or whitespaces")
    @Size(min = 4, max = 50, message = "Name is not the correct length")
    private String name;

    @NotEmpty(message = "Username can't be null or empty")
    @NotBlank(message = "Username can't be null or whitespaces")
    @Size(min = 4, max = 20, message = "Username is not the correct length")
    private String username;

    @NotEmpty(message = "Email can't be null or empty")
    @NotBlank(message = "Email can't be null or whitespaces")
    @Email(message = "Email is not correct")
    private String email;
}
