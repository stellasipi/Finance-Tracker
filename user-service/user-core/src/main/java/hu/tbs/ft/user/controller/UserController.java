package hu.tbs.ft.user.controller;


import hu.tbs.ft.user.model.dto.ModifyUserDTO;
import hu.tbs.ft.user.model.dto.RegisterDTO;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserPasswordDTO;
import hu.tbs.ft.user.service.UserException;
import hu.tbs.ft.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController { // TODO auth után javítani és eltávolítani az id-s részeket

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable UUID id) {
        UserDTO user = userService.getInfo(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO, UriComponentsBuilder uriComponentsBuilder, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                UserDTO userDTO = userService.registerUser(registerDTO);
                UriComponents uriComponents = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userDTO.getId());
                return ResponseEntity.created(uriComponents.toUri()).body(userDTO);
            } catch (UserException ex) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> modifyUser(@PathVariable UUID id, @Valid @RequestBody ModifyUserDTO modifyUserDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                UserDTO modifiedUser = userService.modifyUser(id, modifyUserDTO);
                return ResponseEntity.ok(modifiedUser);
            } catch (UserException ex) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity modifyPassword(@PathVariable UUID id, @Valid @RequestBody UserPasswordDTO userPasswordDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                UserDTO modifiedUser = userService.modifyPassword(id, userPasswordDTO);
                return ResponseEntity.ok(modifiedUser);
            } catch (UserException ex) {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
