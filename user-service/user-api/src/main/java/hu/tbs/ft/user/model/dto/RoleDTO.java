package hu.tbs.ft.user.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoleDTO {
    private UUID id;
    private String name;
}
