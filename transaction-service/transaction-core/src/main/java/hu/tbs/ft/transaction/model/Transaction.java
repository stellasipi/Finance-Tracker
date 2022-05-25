package hu.tbs.ft.transaction.model;

import hu.tbs.ft.transaction.util.PaymentType;
import hu.tbs.ft.transaction.util.ProceedType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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
    private Date createDate;
}
