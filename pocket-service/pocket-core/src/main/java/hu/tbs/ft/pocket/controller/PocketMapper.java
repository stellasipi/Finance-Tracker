package hu.tbs.ft.pocket.controller;

import hu.tbs.ft.pocket.PocketDTO;
import hu.tbs.ft.pocket.model.Pocket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PocketMapper {
    Pocket pocketDtoToPocket(PocketDTO pocketDTO);

    PocketDTO pocketToPocketDto(Pocket pocket);
}
