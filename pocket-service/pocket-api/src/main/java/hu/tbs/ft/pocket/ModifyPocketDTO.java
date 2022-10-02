package hu.tbs.ft.pocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPocketDTO {

    private String name;

    private List<UUID> userId;
}
