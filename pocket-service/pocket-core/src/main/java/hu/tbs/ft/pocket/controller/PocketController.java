package hu.tbs.ft.pocket.controller;

import hu.tbs.ft.pocket.LimitDTO;
import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.PocketServiceIF;
import hu.tbs.ft.pocket.service.PocketService;
import hu.tbs.ft.pocket.util.PocketException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pocket")
@AllArgsConstructor
@Slf4j
public class PocketController implements PocketServiceIF {

    private PocketService pocketService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PocketDTO> getPocketById(@PathVariable UUID id) {
        try {
            PocketDTO pocket = pocketService.getPocketById(id);
            return ResponseEntity.ok(pocket);
        } catch (PocketException ex) {
            log.debug(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PocketDTO>> getAllPocketsForUser(JwtAuthenticationToken principal) {
        try {
            List<PocketDTO> pockets = pocketService.getAllPocketsForUser(principal.getName());
            return ResponseEntity.ok(pockets);
        }catch (PocketException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PocketDTO> createPocket(@RequestBody PocketDTO pocketDTO, UriComponentsBuilder uriComponentsBuilder, JwtAuthenticationToken principal) {
        PocketDTO newPocketDTO = pocketService.createPocket(pocketDTO, principal.getName());
        UriComponents uriComponents = uriComponentsBuilder.path("/pocket/{id}").buildAndExpand(newPocketDTO.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(newPocketDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity<PocketDTO> modifyPocket(@RequestParam UUID id, @RequestBody PocketDTO pocketDTO, JwtAuthenticationToken principal) {
        try {
            PocketDTO modifiedPocket = pocketService.modifyPocket(id, pocketDTO, principal.getName());
            return ResponseEntity.ok(modifiedPocket);
        } catch (PocketException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/limit")
    public ResponseEntity<PocketDTO> createPocketLimit(@RequestParam UUID id, @RequestBody LimitDTO limitDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/limit/delete")
    public ResponseEntity deletePocketLimit(@RequestParam UUID id, @RequestParam UUID limitId) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePocket(@RequestParam UUID id, JwtAuthenticationToken principal) {
        try {
            pocketService.deletePocket(id, principal.getName());
            return ResponseEntity.ok().build();
        } catch (PocketException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
