package hu.tbs.ft.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserPasswordDTO {
    @NotEmpty(message = "Username can't be null or empty")
    @NotBlank(message = "Username can't be null or whitespaces")
    @Size(min = 4, max = 20, message = "Username is not the correct length")
    private String username;

    @NotEmpty(message = "Old password can't be null or empty")
    @NotBlank(message = "Old password can't be null or whitespaces")
    private String oldPassword;

    @NotEmpty(message = "New password can't be null or empty")
    @NotBlank(message = "New password can't be null or whitespaces")
    @Size(min = 6, max = 20, message = "New password is not the correct length")
    private String newPassword;
}
