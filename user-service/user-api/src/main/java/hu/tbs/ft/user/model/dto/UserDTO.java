package hu.tbs.ft.user.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private String name;
    private String username;
    private String email;
    private String registrationDate;
    private List<String> roles=new ArrayList<>();
}
