package hu.tbs.ft.user.service;

import hu.tbs.ft.user.model.User;
import hu.tbs.ft.user.model.dto.RegisterDTO;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserPasswordDTO;
import hu.tbs.ft.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserDTO getInfo(UUID id) {
        User user = userRepository.getById(id);
        return userToUserDTO(user);
    }

    public UserDTO registerUser(RegisterDTO registerDTO) throws UserAlreadyExists {
        if (isNewUserCorrect(registerDTO)) {
            User user = new User(UUID.randomUUID(),
                    registerDTO.getName(),
                    registerDTO.getUsername(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    registerDTO.getEmail(),
                    LocalDateTime.now(),
                    null,
                    null);

            return userToUserDTO(userRepository.save(user));
        } else {
            throw new UserAlreadyExists("Username or e-mail is reserved");
        }
    }

    public UserDTO modifyUser(UUID id, UserDTO userDTO) {
        //TODO
        return null;
    }

    public Boolean modifyPassword(UUID id, UserPasswordDTO userPasswordDTO) {
        //TODO
        return null;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserDTO userToUserDTO(User user) { //TODO javítani a mapperekkel
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), null, null);
    }

    private Boolean isNewUserCorrect(RegisterDTO registerDTO) {
        return true;
    }

}
