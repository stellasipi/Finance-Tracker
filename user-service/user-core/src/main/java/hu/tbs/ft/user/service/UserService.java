package hu.tbs.ft.user.service;

import hu.tbs.ft.user.controller.UserMapper;
import hu.tbs.ft.user.model.User;
import hu.tbs.ft.user.model.dto.ModifyUserDTO;
import hu.tbs.ft.user.model.dto.RegisterDTO;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserPasswordDTO;
import hu.tbs.ft.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    @Value("${finance-tracker.user-service.role.user}")
    private String roleUser;

    @Value("${finance-tracker.user-service.role.admin}")
    private String roleAdmin;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO getInfo(UUID id) {
        log.info("Get user info for {}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userMapper.userToUserDTO(user.get());
        } else {
            log.error("Can't find {} user", id);
            return null;
        }
    }

    public UserDTO registerUser(RegisterDTO registerDTO) throws UserException {
        log.info("Register user");
        if (isUserFieldsCorrectOnCreate(registerDTO)) {
            User user = new User(UUID.randomUUID(),
                    registerDTO.getName(),
                    registerDTO.getUsername(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    registerDTO.getEmail(),
                    LocalDateTime.now(),
                    roleUser);
            log.info("User created {}", user);
            return userMapper.userToUserDTO(userRepository.save(user));
        } else {
            log.error("Can't create user");
            throw new UserException("Not unique fields");
        }
    }

    public UserDTO modifyUser(UUID id, ModifyUserDTO modifyUserDTO) throws UserException {
        log.info("Modify user");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && isUserFieldsCorrectOnUpdate(modifyUserDTO, id)) {
            user.get().setName(modifyUserDTO.getName());
            user.get().setUsername(modifyUserDTO.getUsername());
            user.get().setEmail(modifyUserDTO.getEmail());

            log.info("User modified {}", id);
            return userMapper.userToUserDTO(userRepository.save(user.get()));
        } else {
            log.error("Can't modify user {} or user is not exists", id);
            throw new UserException("Not unique fields or user is not exists");
        }
    }

    public UserDTO modifyPassword(UUID id, UserPasswordDTO userPasswordDTO) throws UserException {
        log.info("Modify password");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && isPasswordModificationIsCorrect(userPasswordDTO, user.get())) {

            user.get().setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));

            log.info("User password modified {}", id);
            return userMapper.userToUserDTO(userRepository.save(user.get()));
        } else {
            log.error("Can't modify password {} or user is not exists", id);
            throw new UserException("Invalid password or user is not exists");
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

    private Boolean isUserFieldsCorrectOnCreate(RegisterDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            log.error("{} username is not unique", userDTO.getUsername());
            return false;
        }
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            log.error("{} email is not unique", userDTO.getEmail());
            return false;
        }
        return true;
    }

    private Boolean isUserFieldsCorrectOnUpdate(ModifyUserDTO userDTO, UUID id) {
        if (userRepository.findByUsernameAndIdNot(userDTO.getUsername(), id).isPresent()) {
            log.error("{} username is not unique", userDTO.getUsername());
            return false;
        }
        if (userRepository.findByEmailAndIdNot(userDTO.getEmail(), id).isPresent()) {
            log.error("{} email is not unique", userDTO.getEmail());
            return false;
        }
        return true;
    }

    private Boolean isPasswordModificationIsCorrect(UserPasswordDTO userPasswordDTO, User user) {
        if (!passwordEncoder.matches(userPasswordDTO.getOldPassword(), user.getPassword())) {
            log.error("{} user old password is not correct", user.getId());
            return false;
        }

        if (passwordEncoder.matches(userPasswordDTO.getNewPassword(), user.getPassword())) {
            log.error("{} user new password is matches with the new one", user.getId());
            return false;
        }
        return true;
    }

}
