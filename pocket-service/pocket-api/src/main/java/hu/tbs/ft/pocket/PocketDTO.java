package hu.tbs.ft.pocket;

import hu.tbs.ft.planning.RepetitionDTO;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PocketDTO {
    private UUID id;
    private String name;
    private String currency;
    private List<LimitDTO> limits;
    private List<RepetitionDTO> repetitons;
}
