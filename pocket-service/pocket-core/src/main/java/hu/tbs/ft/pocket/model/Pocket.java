package hu.tbs.ft.pocket.model;

import hu.tbs.ft.pocket.util.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pocket {
    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "pocket", orphanRemoval = true)
    private List<PocketUser> users;
}
