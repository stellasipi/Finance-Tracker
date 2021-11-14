package hu.tbs.ft.user.controller;


import hu.tbs.ft.user.model.dto.*;
import hu.tbs.ft.user.service.UserException;
import hu.tbs.ft.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController implements UserServiceIF {

    private UserService userService;

    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findOneUser(@PathVariable UUID id) {
        return userService.findOne(id)
                .map(entity -> ResponseEntity.ok(userMapper.userToUserDTO(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            UserDTO userDTO = userService.registerUser(registerDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userDTO.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(userDTO);
        } catch (UserException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> modifyUser(@PathVariable UUID id, @Valid @RequestBody ModifyUserDTO modifyUserDTO) {
        try {
            UserDTO modifiedUser = userService.modifyUser(id, modifyUserDTO);
            return ResponseEntity.ok(modifiedUser);
        } catch (UserException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity modifyPassword(@PathVariable UUID id, @Valid @RequestBody UserPasswordDTO userPasswordDTO) {
        try {
            UserDTO modifiedUser = userService.modifyPassword(id, userPasswordDTO);
            return ResponseEntity.ok(modifiedUser);
        } catch (UserException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
