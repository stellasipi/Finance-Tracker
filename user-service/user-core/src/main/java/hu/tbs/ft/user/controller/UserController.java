package hu.tbs.ft.user.controller;


import hu.tbs.ft.user.model.dto.RegisterDTO;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserPasswordDTO;
import hu.tbs.ft.user.service.UserAlreadyExists;
import hu.tbs.ft.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping//("/user")
@AllArgsConstructor
public class UserController { // TODO auth után javítani

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable UUID id) {
        UserDTO user = userService.getInfo(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterDTO registerDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            UserDTO userDTO = userService.registerUser(registerDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userDTO.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(userDTO);
        } catch (UserAlreadyExists ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> modifyUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity modifyPassword(@RequestBody UserPasswordDTO userDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok().build();
    }

}
