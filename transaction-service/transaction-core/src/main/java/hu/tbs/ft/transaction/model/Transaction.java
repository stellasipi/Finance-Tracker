package hu.tbs.ft.transaction.model;

import hu.tbs.ft.transaction.util.PaymentType;
import hu.tbs.ft.transaction.util.ProceedType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID pocketId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProceedType proceedType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @PrePersist
    private void setFields() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

        if (this.createDate == null) {
            this.createDate = LocalDateTime.now();
        }
    }
}
