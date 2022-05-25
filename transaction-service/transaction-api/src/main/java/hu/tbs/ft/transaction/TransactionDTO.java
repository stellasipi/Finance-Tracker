package hu.tbs.ft.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private UUID id;
    private UUID userId;
    private String proceedType;
    private String paymentType;
    private Double amount;
    private String name;
    private String description;
    private Date createDate;
}
