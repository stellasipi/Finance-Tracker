package hu.tbs.ft.report.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Report {
    @Id
    private UUID id = UUID.randomUUID();
}
