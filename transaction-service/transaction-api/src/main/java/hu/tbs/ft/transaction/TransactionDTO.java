package hu.tbs.ft.transaction;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDTO extends ModifyTransactionDTO {

    private UUID id;

    private UUID userId;

    private String creatorUsername;

    private Date createDate;
}
