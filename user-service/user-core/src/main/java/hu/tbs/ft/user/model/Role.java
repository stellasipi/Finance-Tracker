package hu.tbs.ft.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"role\"")
public class Role {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

}
