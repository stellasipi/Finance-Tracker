package hu.tbs.ft.planning.controller;

import hu.tbs.ft.planning.ModifyReminderDTO;
import hu.tbs.ft.planning.ReminderDTO;
import hu.tbs.ft.planning.service.ReminderService;
import hu.tbs.ft.planning.util.ReminderException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reminder")
@AllArgsConstructor
public class ReminderController {

    private ReminderService reminderService;

    @GetMapping("/all")
    public ResponseEntity<List<ReminderDTO>> getAllRemindersForUser(JwtAuthenticationToken principal) {
        try {
            List<ReminderDTO> reminders = reminderService.getAllReminderForUser(principal.getName());
            return ResponseEntity.ok(reminders);
        } catch (ReminderException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<ReminderDTO> getReminderById(@RequestParam UUID id, JwtAuthenticationToken principal) {
        try {
            ReminderDTO reminder = reminderService.getReminderById(id, principal.getName());
            return ResponseEntity.ok(reminder);
        } catch (ReminderException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReminderDTO> createReminder(@Valid @RequestBody ReminderDTO reminderDTO, UriComponentsBuilder uriComponentsBuilder, JwtAuthenticationToken principal) {
        ReminderDTO newReminder = reminderService.createReminder(reminderDTO, principal.getName());
        UriComponents uriComponents = uriComponentsBuilder.path("/reminder?id={id}").buildAndExpand(newReminder.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(newReminder);
    }

    @PutMapping("/modify")
    public ResponseEntity<ReminderDTO> modifyReminder(@RequestParam UUID id, @Valid @RequestBody ModifyReminderDTO reminderDTO, JwtAuthenticationToken principal) {
        try {
            ReminderDTO reminder = reminderService.modifyReminder(id, reminderDTO, principal.getName());
            return ResponseEntity.ok(reminder);
        } catch (ReminderException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteReminder(@RequestParam UUID id, JwtAuthenticationToken principal) {
        try {
            reminderService.deleteReminder(id, principal.getName());
            return ResponseEntity.ok().build();
        } catch (ReminderException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
