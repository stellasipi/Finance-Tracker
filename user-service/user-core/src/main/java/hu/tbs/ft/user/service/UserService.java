package hu.tbs.ft.user.service;

import hu.tbs.ft.user.controller.UserMapper;
import hu.tbs.ft.user.model.User;
import hu.tbs.ft.user.model.dto.*;
import hu.tbs.ft.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

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

    public Optional<User> findOne(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<DbUser> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return Optional.of(userMapper.userToDbUser(user.get()));
        }
        return Optional.empty();
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
            log.info("User created with username: {}", user.getUsername());
            return userMapper.userToUserDTO(userRepository.save(user));
        } else {
            log.error("Can't create user");
            throw new UserException("Not unique fields");
        }
    }

    public UserDTO modifyUser(JwtAuthenticationToken authentication, ModifyUserDTO modifyUserDTO) throws UserException {
        log.info("Modify user");
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (user.isPresent() && isUserFieldsCorrectOnUpdate(modifyUserDTO, user.get().getId())) {
            user.get().setName(modifyUserDTO.getName());
            user.get().setUsername(modifyUserDTO.getUsername());
            user.get().setEmail(modifyUserDTO.getEmail());

            log.info("User modified {}", user.get().getUsername());
            return userMapper.userToUserDTO(userRepository.save(user.get()));
        } else {
            log.error("Can't modify user {} or user is not exists", authentication.getName());
            throw new UserException("Not unique fields or user is not exists");
        }
    }

    public UserDTO modifyPassword(JwtAuthenticationToken authentication, UserPasswordDTO userPasswordDTO) throws UserException {
        log.info("Modify password");
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        if (user.isPresent() && isPasswordModificationIsCorrect(userPasswordDTO, user.get())) {

            user.get().setPassword(passwordEncoder.encode(userPasswordDTO.getNewPassword()));

            log.info("User password modified with username:{}", user.get().getUsername());
            return userMapper.userToUserDTO(userRepository.save(user.get()));
        } else {
            log.error("Can't modify password for {} or user is not exists", authentication.getName());
            throw new UserException("Invalid password or user is not exists");
        }
    }

    public Boolean deleteUser(JwtAuthenticationToken authenticationToken) {
        Optional<User> user = userRepository.findByUsername(authenticationToken.getName());
        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            log.info("{} user deleted", user.get().getUsername());
            return true;
        } else {
            log.error("Can't find {} user", authenticationToken.getName());
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
