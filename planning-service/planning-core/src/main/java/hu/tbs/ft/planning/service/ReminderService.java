package hu.tbs.ft.planning.service;

import hu.tbs.ft.planning.ModifyReminderDTO;
import hu.tbs.ft.planning.ReminderDTO;
import hu.tbs.ft.planning.controller.ReminderMapper;
import hu.tbs.ft.planning.model.Reminder;
import hu.tbs.ft.planning.repository.ReminderRepository;
import hu.tbs.ft.planning.util.ReminderException;
import hu.tbs.ft.user.model.dto.DbUser;
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
public class ReminderService {

    private final ReminderRepository reminderRepository;

    private final ReminderMapper reminderMapper;

    private UserServiceIF userServiceIF;

    public ReminderDTO getReminderById(UUID id, String loggedInUsername) throws ReminderException {
        Optional<Reminder> reminder = reminderRepository.findById(id);
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        if (reminder.isPresent() && reminder.get().isOwner(loggedInUser.getId())) {
            return reminderMapper.reminderToReminderDto(reminder.get());
        } else {
            log.debug("Reminder is not present and/or requester is not the owner");
            throw new ReminderException("Reminder is not present and/or requester is not the owner");
        }
    }

    public List<ReminderDTO> getAllReminderForUser(String loggedInUsername) throws ReminderException {
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        return reminderRepository.findByUserId(loggedInUser.getId()).stream().map(reminderMapper::reminderToReminderDto).collect(Collectors.toList());
    }

    public ReminderDTO createReminder(ReminderDTO reminderDTO, String loggedInUsername) {
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        Reminder reminder = reminderMapper.reminderDtoToReminder(reminderDTO);
        reminder.setUserId(loggedInUser.getId());
        reminderRepository.save(reminder);
        return reminderMapper.reminderToReminderDto(reminder);
    }

    public ReminderDTO modifyReminder(UUID id, ModifyReminderDTO reminderDTO, String loggedInUsername) throws ReminderException {
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        Optional<Reminder> reminder = reminderRepository.findById(id);
        if (reminder.isPresent() && reminder.get().isOwner(loggedInUser.getId())) {
            reminder.get().setName(reminderDTO.getName());
            reminder.get().setDeadline(reminderDTO.getDeadline());
            if (reminderDTO.getDescription() != null) {
                reminder.get().setDescription(reminderDTO.getDescription());
            }
            reminderRepository.save(reminder.get());
            return reminderMapper.reminderToReminderDto(reminder.get());
        } else {
            log.debug("Reminder is not present and/or requester is not the owner");
            throw new ReminderException("Reminder is not present and/or requester is not the owner");
        }
    }

    public void deleteReminder(UUID id, String loggedInUsername) throws ReminderException {
        Optional<Reminder> reminder = reminderRepository.findById(id);
        DbUser loggedInUser = userServiceIF.findUserByUsername(loggedInUsername).getBody();
        if (reminder.isPresent() && reminder.get().isOwner(loggedInUser.getId())) {
            reminderRepository.deleteById(id);
        } else {
            log.debug("Reminder is not present and/or requester is not the owner");
            throw new ReminderException("Reminder is not present and/or requester is not the owner");
        }
    }

}
