package hu.tbs.ft.planning.controller;

import hu.tbs.ft.planning.ReminderDTO;
import hu.tbs.ft.planning.model.Reminder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReminderMapper {
    Reminder reminderDtoToReminder(ReminderDTO reminderDTO);
    ReminderDTO reminderToReminderDto(Reminder reminder);
}
