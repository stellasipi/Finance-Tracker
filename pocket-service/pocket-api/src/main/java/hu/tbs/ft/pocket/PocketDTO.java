package hu.tbs.ft.pocket;

import hu.tbs.ft.planning.RepetitionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PocketDTO {
    private UUID id;
    private String name;
    private String currency;
    private List<UUID> userId;
    //private List<LimitDTO> limits;
    //private List<RepetitionDTO> repetitons;
}
