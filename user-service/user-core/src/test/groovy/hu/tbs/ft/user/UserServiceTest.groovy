package hu.tbs.ft.user

import hu.tbs.ft.user.controller.UserMapper
import hu.tbs.ft.user.model.User
import hu.tbs.ft.user.model.dto.DbUser
import hu.tbs.ft.user.model.dto.ModifyUserDTO
import hu.tbs.ft.user.model.dto.RegisterDTO
import hu.tbs.ft.user.model.dto.UserDTO
import hu.tbs.ft.user.repository.UserRepository
import hu.tbs.ft.user.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime

class UserServiceTest extends Specification {

    private UserService userService

    private UserRepository userRepository

    private PasswordEncoder passwordEncoder

    private UserMapper userMapper

    private JwtAuthenticationToken authentication

    def setup() {
        userRepository = Mock(UserRepository)
        passwordEncoder = new BCryptPasswordEncoder()
        userMapper = Mock(UserMapper)
        authentication = new JwtAuthenticationToken(
                new Jwt("token", Instant.now(), Instant.now().plusSeconds(600000), Map.of("alg", "none"), Map.of("sub", "subject")))
        userService = new UserService(userRepository, passwordEncoder, userMapper)
    }

    def "find user by id"() {
        given:
        def user = aUser()
        userRepository.findById(user.id) >> Optional.of(user)

        when:
        def foundUser = userService.findOne(user.id)

        then:
        assertUser(foundUser.get(), user)
    }

    def "find user by username"() {
        given:
        def user = aUser()
        userRepository.findByUsername(user.username) >> Optional.of(user)
        userMapper.userToDbUser(user) >> DbUser.builder()
                .id(user.id)
                .name(user.name)
                .username(user.username)
                .email(user.email)
                .role(user.role)
                .password(user.password)
                .build()

        when:
        def result = userService.findByUsername(user.username)

        then:
        1 * userRepository.findByUsername(user.username) >> Optional.of(user)
        with(result.get()) {
            def foundUser = User.builder()
                    .name(it.name)
                    .username(it.username)
                    .email(it.email)
                    .role(it.role)
                    .password(it.password)
                    .build()
            assertUser(foundUser, user)
        }
    }

    def "get the logged in user"() {
        given:
        def user = aUser()
        userRepository.findByUsername(authentication.getName()) >> Optional.of(user)
        userMapper.userToUserDTO(user) >> toUserDto(user)

        when:
        def foundUser = userService.getLoggedInUser(authentication)

        then:
        assertUser(toUser(foundUser.get()), user)
    }

    def "register user"() {
        given:
        def user = aUser()
        def registerDto = RegisterDTO.builder()
                .username(user.username)
                .name(user.name)
                .email(user.email)
                .password(user.password)
                .build()
        userRepository.findByUsername(registerDto.username) >> Optional.empty()
        userRepository.findByEmail(registerDto.email) >> Optional.empty()
        userMapper.userToUserDTO(userRepository.save(user)) >> toUserDto(user)

        when:
        def result = userService.registerUser(registerDto)

        then:
        assertUser(toUser(result), user)
    }

    def "modify user"() {
        given:
        def user = aUser()
        def modifyUserDto = ModifyUserDTO.builder()
                .username(user.username)
                .name(user.name)
                .email("new.email@test.com")
                .build()
        userRepository.findByUsername(authentication.getName()) >> Optional.of(user)
        userRepository.findByUsernameAndIdNot(modifyUserDto.username, user.id) >> Optional.empty()
        userRepository.findByEmailAndIdNot(modifyUserDto.email, user.id) >> Optional.empty()
        userMapper.userToUserDTO(userRepository.save(user)) >> toUserDto(user)

        when:
        def result = userService.modifyUser(authentication, modifyUserDto)

        then:
        def expectedUser = User.builder()
                .id(user.id)
                .name(user.name)
                .username(user.username)
                .email(modifyUserDto.email)
                .password(user.password)
                .role(user.role)
                .build()
        assertUser(toUser(result), expectedUser)
    }

    def "delete user"() {
        given:
        def user = aUser()
        userRepository.findByUsername(authentication.getName()) >> Optional.of(user)

        when:
        def result = userService.deleteUser(authentication)

        then:
        1 * userRepository.deleteById(user.id)
        result == true
    }

    private User aUser(String name = "Alice", String username = "alice.test", String email = "alice.test@test.com", String password = "password") {
        return User.builder()
                .id(UUID.randomUUID())
                .name(name)
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role("USER")
                .registrationDate(LocalDateTime.now())
                .build()
    }

    private void assertUser(User currentUser, User expectedUser) {
        currentUser.id == expectedUser.id
        currentUser.name == expectedUser.name
        currentUser.email == expectedUser.email
        if (currentUser.password != null) {
            passwordEncoder.matches(currentUser.password, expectedUser.password)
        }
        if (currentUser.registrationDate != null) {
            currentUser.registrationDate == expectedUser.registrationDate
        }
    }

    private UserDTO toUserDto(User user) {
        return UserDTO.builder()
                .id(user.id)
                .name(user.name)
                .username(user.username)
                .email(user.email)
                .role(user.role)
                .build()
    }

    private User toUser(UserDTO dto) {
        return User.builder()
                .id(dto.id)
                .name(dto.name)
                .username(dto.username)
                .email(dto.email)
                .role(dto.role)
                .build()
    }
}
