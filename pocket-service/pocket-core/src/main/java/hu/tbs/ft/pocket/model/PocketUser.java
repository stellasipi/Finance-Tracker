package hu.tbs.ft.pocket.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "pocket_id"})})
public class PocketUser {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "pocket_id", nullable = false)
    private Pocket pocket;
}
