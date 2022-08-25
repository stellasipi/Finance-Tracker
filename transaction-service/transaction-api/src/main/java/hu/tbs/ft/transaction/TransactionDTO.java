package hu.tbs.ft.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private UUID id;

    private UUID userId;

    private UUID pocketId;

    @Pattern(regexp = "INCOME|EXPENDITURE", message = "Proceed type is not from the allowed types")
    private String proceedType;

    @Pattern(regexp = "CASH|CARD", message = "Payment type is not from the allowed types")
    private String paymentType;

    @Min(value = 0L, message = "The amount must be positive")
    private Double amount;

    @NotEmpty(message = "Name can't be null or empty")
    @NotBlank(message = "Name can't be null or whitespaces")
    @Size(min = 4, max = 50, message = "Name is not the correct length")
    private String name;

    private String description;

    private Date createDate;
}
