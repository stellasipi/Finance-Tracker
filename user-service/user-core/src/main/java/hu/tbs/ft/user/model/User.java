package hu.tbs.ft.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class User {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @ElementCollection
    private List<UUID> roles;

    @ElementCollection
    private List<UUID> reports;

    @ElementCollection
    private List<UUID> reminders;

    @ElementCollection
    private List<UUID> transactions;

    @ElementCollection
    private List<UUID> pockets;

}
