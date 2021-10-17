package hu.tbs.ft.pocket.controller;

import hu.tbs.ft.pocket.LimitDTO;
import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.service.PocketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pocket")
@AllArgsConstructor
public class PocketController {

    private PocketService pocketService;

    @GetMapping
    public ResponseEntity<List<PocketDTO>> getAllPockets() { //TODO + filterek
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PocketDTO> getPocket(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
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
