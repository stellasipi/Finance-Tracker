package hu.tbs.ft.planning.controller;

import hu.tbs.ft.planning.RepetitionDTO;
import hu.tbs.ft.planning.service.RepetitionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/repetition")
@AllArgsConstructor
public class RepetitionController {

    @Autowired
    private RepetitionService repetitionService;

    @GetMapping("/{pocketId}")
    public ResponseEntity<List<RepetitionDTO>> getAllRepetitionsForPocket(@PathVariable UUID pocketId) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PostMapping
    public ResponseEntity<RepetitionDTO> createRepetition(@RequestBody RepetitionDTO repetitionDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepetitionDTO> modifyRepetition(@PathVariable UUID id, @RequestBody RepetitionDTO repetitionDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRepetition(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
