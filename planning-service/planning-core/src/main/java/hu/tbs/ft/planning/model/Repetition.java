package hu.tbs.ft.planning.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Repetition {
    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    private LocalDateTime createdDate;

    private String recurrence;

    private String description;
}
