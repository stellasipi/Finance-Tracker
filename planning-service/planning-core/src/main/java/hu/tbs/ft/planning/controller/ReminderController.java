package hu.tbs.ft.planning.controller;

import hu.tbs.ft.planning.ReminderDTO;
import hu.tbs.ft.planning.service.ReminderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reminder")
@AllArgsConstructor
public class ReminderController {

    private ReminderService reminderService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReminderDTO>> getAllRemindersForUser(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PostMapping
    public ResponseEntity<ReminderDTO> createReminder(@RequestBody ReminderDTO reminderDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> modifyReminder(@PathVariable UUID id, @RequestBody ReminderDTO reminderDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReminder(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
