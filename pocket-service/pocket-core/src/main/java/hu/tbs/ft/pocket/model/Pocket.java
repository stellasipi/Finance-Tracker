package hu.tbs.ft.pocket.model;

import hu.tbs.ft.pocket.util.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pocket {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "pocket", orphanRemoval = true)
    private List<PocketUser> users;

    @PrePersist
    private void automaticallySetId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public void addUser(PocketUser user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }

    public boolean isUserAnOwner(UUID userId) {
        for (PocketUser user : users) {
            if (user.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
