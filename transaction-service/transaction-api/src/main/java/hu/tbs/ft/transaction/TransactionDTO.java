package hu.tbs.ft.transaction;

import hu.tbs.ft.user.model.dto.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID id;
    private List<UserDTO> users;
    private String proceedsType;
    private String paymentType;
    private Double amount;
    private String name;
    private String description;
    private LocalDateTime createDate;
}
