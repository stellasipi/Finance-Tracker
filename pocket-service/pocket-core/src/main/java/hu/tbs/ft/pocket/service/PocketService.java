package hu.tbs.ft.pocket.service;

import hu.tbs.ft.pocket.repository.PocketRepository;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PocketService {

    private PocketRepository pocketRepository;

    private UserServiceIF userServiceIF;

    public UserDTO testFeign(UUID id) {
        UserDTO userDTO = userServiceIF.findOneUser(id).getBody();
        return userDTO;
    }

}
