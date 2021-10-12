package hu.tbs.ft.planning.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Reminder {
    @Id
    private UUID id = UUID.randomUUID();
}
