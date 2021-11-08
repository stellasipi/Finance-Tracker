package hu.tbs.ft.user.model.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service",
        url = "${services.user-service-url:localhost:8081}/user")
public interface UserServiceIF {
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findOneUser(@PathVariable UUID id);

    @GetMapping("/test")
    public ResponseEntity test();
}
