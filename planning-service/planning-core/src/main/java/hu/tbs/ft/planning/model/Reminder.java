package hu.tbs.ft.planning.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Reminder {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime deadline;

    private String description;

    @PrePersist
    private void automaticallySetFields() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    public boolean isOwner(UUID userId) {
        return userId.equals(this.userId);
    }
}
