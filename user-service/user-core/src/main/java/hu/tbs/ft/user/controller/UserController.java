package hu.tbs.ft.user.controller;


import hu.tbs.ft.user.model.dto.*;
import hu.tbs.ft.user.util.UserException;
import hu.tbs.ft.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController implements UserServiceIF {

    private UserService userService;

    private UserMapper userMapper;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> findOneUser(@PathVariable UUID id) {
        return userService.findOne(id)
                .map(entity -> ResponseEntity.ok(userMapper.userToUserDTO(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<DbUser> findUserByUsername(@Valid @PathVariable @NotNull @NotBlank @NotEmpty String username) {
        Optional<DbUser> user = userService.findByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
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

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getLoggedInUser(JwtAuthenticationToken authentication) {
        Optional<UserDTO> user = userService.getLoggedInUser(authentication);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> modifyUser(JwtAuthenticationToken authentication, @Valid @RequestBody ModifyUserDTO modifyUserDTO) {
        try {
            UserDTO modifiedUser = userService.modifyUser(authentication, modifyUserDTO);
            return ResponseEntity.ok(modifiedUser);
        } catch (UserException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/password")
    public ResponseEntity modifyPassword(JwtAuthenticationToken authentication, @Valid @RequestBody UserPasswordDTO userPasswordDTO) {
        try {
            UserDTO modifiedUser = userService.modifyPassword(authentication, userPasswordDTO);
            return ResponseEntity.ok(modifiedUser);
        } catch (UserException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Secured("ADMIN")
    public ResponseEntity deleteUser(JwtAuthenticationToken authentication) {
        if (userService.deleteUser(authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
