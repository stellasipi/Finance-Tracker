package hu.tbs.ft.pocket.service;

import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.controller.PocketMapper;
import hu.tbs.ft.pocket.repository.PocketRepository;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PocketService {

    private final PocketRepository pocketRepository;

    private final PocketMapper pocketMapper;

    private UserServiceIF userServiceIF;

    public UserDTO testFeign(UUID id) {
        return userServiceIF.findOneUser(id).getBody();
    }

    public List<PocketDTO> getAllPocketsForUser(UUID userId) {
        return pocketRepository.findByUsersUserId(userId).stream().map(pocketMapper::pocketToPocketDto).collect(Collectors.toList());
    }

}
