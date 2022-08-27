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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<PocketDTO>> getAllPocketsForUser(@RequestParam UUID userId) { //TODO + filterek
        return ResponseEntity.ok(pocketService.getAllPocketsForUser(userId));
    }

    @PostMapping
    public ResponseEntity<PocketDTO> createPocket(@RequestBody PocketDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PocketDTO> modifyPocket(@PathVariable UUID id, @RequestBody PocketDTO pocketDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PostMapping("/{id}/limit")
    public ResponseEntity<PocketDTO> createPocketLimit(@PathVariable UUID id, @RequestBody LimitDTO limitDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}/limit/{limitId}")
    public ResponseEntity deletePocketLimit(@PathVariable UUID id, @PathVariable UUID limitId) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePocket(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
