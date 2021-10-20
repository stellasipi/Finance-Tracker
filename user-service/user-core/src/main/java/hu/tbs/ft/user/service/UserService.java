package hu.tbs.ft.user.service;

import hu.tbs.ft.user.model.User;
import hu.tbs.ft.user.model.dto.ModifyUserDTO;
import hu.tbs.ft.user.model.dto.RegisterDTO;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserPasswordDTO;
import hu.tbs.ft.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserDTO getInfo(UUID id) {
        log.info("Get user info for {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userToUserDTO(user.get());
        }else{
            log.error("Can't find {} user", id);
            return null;
        }
    }

    public UserDTO registerUser(RegisterDTO registerDTO) throws UserException { //TODO check, roles, reminders
        log.info("Register user");
        if (isNewUserCorrect(registerDTO)) {
            User user = new User(UUID.randomUUID(),
                    registerDTO.getName(),
                    registerDTO.getUsername(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    registerDTO.getEmail(),
                    LocalDateTime.now(),
                    null,
                    null);
            log.info("User created {}", user);
            return userToUserDTO(userRepository.save(user));
        } else {
            log.error("Can't create user {}", registerDTO);
            throw new UserException("Not unique fields");
        }
    }

    public UserDTO modifyUser(UUID id, ModifyUserDTO modifyUserDTO) throws UserException {
        log.info("Modify user");
        if (isModificationIsCorrect(modifyUserDTO, id)) {
            User user = userRepository.getById(id);

            user.setName(modifyUserDTO.getName());
            user.setUsername(modifyUserDTO.getUsername());
            user.setEmail(modifyUserDTO.getEmail());

            log.info("User modified {}", id);
            return userToUserDTO(userRepository.save(user));
        } else {
            log.error("Can't modify user {}", id);
            throw new UserException("Not unique fields");
        }
    }

    public UserDTO modifyPassword(UUID id, UserPasswordDTO userPasswordDTO) throws UserException {
        log.info("Modify password");
        if (isPasswordModificationIsCorrect(userPasswordDTO, id)) {
            User user = userRepository.getById(id);

            user.setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));

            log.info("User password modified {}", id);
            return userToUserDTO(userRepository.save(user));
        } else {
            log.error("Can't modify password {}", id);
            throw new UserException("Invalid password");
        }
    }

    public Boolean deleteUser(UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            log.info("{} user deleted", id);
            return true;
        } else {
            log.error("Can't find {} user", id);
            return false;
        }
    }

    private UserDTO userToUserDTO(User user) { //TODO javítani a mapperekkel
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), null, null);
    }

    private Boolean isNewUserCorrect(RegisterDTO registerDTO) {
        //TODO unique check
        return true;
    }

    private Boolean isModificationIsCorrect(ModifyUserDTO modifyUserDTO, UUID id) {
        //TODO unique check
        return true;
    }

    private Boolean isPasswordModificationIsCorrect(UserPasswordDTO userPasswordDTO, UUID id) {
        //TODO unique check
        return true;
    }

}
