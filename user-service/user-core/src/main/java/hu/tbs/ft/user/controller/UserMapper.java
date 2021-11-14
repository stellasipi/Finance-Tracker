package hu.tbs.ft.user.controller;

import hu.tbs.ft.user.model.User;
import hu.tbs.ft.user.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
}
