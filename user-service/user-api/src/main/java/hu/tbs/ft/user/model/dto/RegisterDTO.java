package hu.tbs.ft.user.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private String registrationDate;
}
