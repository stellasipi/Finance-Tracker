package hu.tbs.ft.pocket;

import lombok.Data;

import java.util.UUID;

@Data
public class LimitDTO {
    private UUID id;
    private String name;
    private String type;
    private Double maxAmount;
}
