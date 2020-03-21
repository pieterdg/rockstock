package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.MineralDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mineral} and its DTO {@link MineralDTO}.
 */
@Mapper(componentModel = "spring", uses = {NickelStrunzLevelThreeMapper.class})
public interface MineralMapper extends EntityMapper<MineralDTO, Mineral> {

    @Mapping(source = "nickelStrunzLevelThree.id", target = "nickelStrunzLevelThreeId")
    @Mapping(source = "nickelStrunzLevelThree.name", target = "nickelStrunzLevelThreeName")
    MineralDTO toDto(Mineral mineral);

    @Mapping(source = "nickelStrunzLevelThreeId", target = "nickelStrunzLevelThree")
    @Mapping(target = "specimens", ignore = true)
    @Mapping(target = "removeSpecimens", ignore = true)
    Mineral toEntity(MineralDTO mineralDTO);

    default Mineral fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mineral mineral = new Mineral();
        mineral.setId(id);
        return mineral;
    }
}
