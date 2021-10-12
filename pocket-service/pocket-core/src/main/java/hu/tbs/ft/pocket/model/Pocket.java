package hu.tbs.ft.pocket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pocket {
    @Id
    private UUID id = UUID.randomUUID();
}
