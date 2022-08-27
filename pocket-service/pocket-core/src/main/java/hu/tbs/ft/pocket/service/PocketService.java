package hu.tbs.ft.pocket.service;

import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.controller.PocketMapper;
import hu.tbs.ft.pocket.model.Pocket;
import hu.tbs.ft.pocket.repository.PocketRepository;
import hu.tbs.ft.pocket.util.PocketException;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PocketService {

    private final PocketRepository pocketRepository;

    private final PocketMapper pocketMapper;

    private UserServiceIF userServiceIF;

    public List<PocketDTO> getAllPocketsForUser(UUID userId) {
        return pocketRepository.findByUsersUserId(userId).stream().map(pocketMapper::pocketToPocketDto).collect(Collectors.toList());
    }

    public PocketDTO getPocketById(UUID pocketId) throws PocketException {
        Optional<Pocket> pocket = pocketRepository.findById(pocketId);
        if (pocket.isPresent()) {
            return pocketMapper.pocketToPocketDto(pocket.get());
        } else {
            throw new PocketException("Pocket is not exists.");
        }
    }

}
