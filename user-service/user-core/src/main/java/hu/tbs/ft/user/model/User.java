package hu.tbs.ft.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    @Id
    private UUID id = UUID.randomUUID();
}
