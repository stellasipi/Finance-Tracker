package hu.tbs.ft.pocket.service;

import hu.tbs.ft.pocket.ModifyPocketDTO;
import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.controller.PocketMapper;
import hu.tbs.ft.pocket.model.Pocket;
import hu.tbs.ft.pocket.model.PocketUser;
import hu.tbs.ft.pocket.repository.PocketRepository;
import hu.tbs.ft.pocket.repository.PocketUserRepository;
import hu.tbs.ft.pocket.util.PocketException;
import hu.tbs.ft.user.model.dto.DbUser;
import hu.tbs.ft.user.model.dto.UserDTO;
import hu.tbs.ft.user.model.dto.UserServiceIF;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PocketService {

    private final PocketRepository pocketRepository;

    private final PocketUserRepository pocketUserRepository;

    private final PocketMapper pocketMapper;

    private UserServiceIF userServiceIF;

    public List<PocketDTO> getAllPocketsForUser(String loggedInUsername) throws PocketException {
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        List<Pocket> pockets = pocketRepository.findByUsersUserId(loggedInUser.getId());
        List<PocketDTO> pocketDTOs = new ArrayList<>();
        pockets.forEach(pocket -> {
            PocketDTO pocketDTO = setUserIds(pocket.getUsers(), pocket);
            pocketDTOs.add(pocketDTO);
        });
        return pocketDTOs;
    }

    public PocketDTO getPocketById(UUID pocketId) throws PocketException {
        Optional<Pocket> pocket = pocketRepository.findById(pocketId);
        if (pocket.isPresent()) {
            return pocketMapper.pocketToPocketDto(pocket.get());
        } else {
            throw new PocketException("Pocket is not exists");
        }
    }

    public PocketDTO createPocket(PocketDTO pocketDTO, String loggedInUsername) {
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        Pocket pocket = pocketMapper.pocketDtoToPocket(pocketDTO);
        pocketRepository.save(pocket);
        PocketUser pocketUser = PocketUser.builder()
                .userId(loggedInUser.getId())
                .pocket(pocket)
                .build();
        pocketUserRepository.save(pocketUser);
        pocket.addUser(pocketUser);
        return setUserIds(List.of(pocketUser), pocket);
    }

    public PocketDTO modifyPocket(UUID pocketId, ModifyPocketDTO pocketDTO, String loggedInUsername) throws PocketException {
        Optional<Pocket> pocket = pocketRepository.findById(pocketId);
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        commonChecks(pocket, loggedInUser);

        Pocket modifiedPocket = pocket.get();
        modifiedPocket.setName(pocketDTO.getName());
        for (UUID userId : pocketDTO.getUserId()) {
            ResponseEntity<UserDTO> contributorResponse = userServiceIF.findOneUser(userId);
            if (contributorResponse.getStatusCode().isError()) {
                throw new PocketException("New user does not exists");
            }
            UserDTO contributor = contributorResponse.getBody();
            if (!modifiedPocket.isUserAnOwner(contributor.getId())) {
                PocketUser pocketUser = PocketUser.builder()
                        .pocket(modifiedPocket)
                        .userId(contributor.getId())
                        .build();
                pocketUserRepository.save(pocketUser);
                modifiedPocket.addUser(pocketUser);
            }
        }

        pocketRepository.save(modifiedPocket);
        return setUserIds(modifiedPocket.getUsers(), modifiedPocket);
    }

    public void deletePocket(UUID id, String loggedInUsername) throws PocketException {
        Optional<Pocket> pocket = pocketRepository.findById(id);
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        commonChecks(pocket, loggedInUser);
        pocketRepository.deleteById(id);
    }

    private void commonChecks(Optional<Pocket> pocket, DbUser loggedInUser) throws PocketException {
        if (pocket.isEmpty()) {
            log.debug("Pocket is not exists");
            throw new PocketException("Pocket is not exists");
        }

        if (!pocket.get().isUserAnOwner(loggedInUser.getId())) {
            log.debug("User is not one of the owner");
            throw new PocketException("User is not one of the owner");
        }
    }

    private PocketDTO setUserIds(List<PocketUser> users, Pocket pocket) {
        PocketDTO pocketDTO = pocketMapper.pocketToPocketDto(pocket);
        pocketDTO.setUserId(new ArrayList<>());
        users.forEach(user -> pocketDTO.getUserId().add(user.getUserId()));
        return pocketDTO;
    }
}
