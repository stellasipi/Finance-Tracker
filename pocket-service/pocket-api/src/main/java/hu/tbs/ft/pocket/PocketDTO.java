package hu.tbs.ft.pocket;

import hu.tbs.ft.planning.RepetitionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
public class PocketDTO extends ModifyPocketDTO {

    private UUID id;

    @Pattern(regexp = "HUF|EUR|USD|GBP", message = "Currency is not from the allowed types")
    private String currency;

    //private List<LimitDTO> limits;
    //private List<RepetitionDTO> repetitons;
}
