package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NickelStrunzLevelThree} and its DTO {@link NickelStrunzLevelThreeDTO}.
 */
@Mapper(componentModel = "spring", uses = {NickelStrunzLevelTwoMapper.class})
public interface NickelStrunzLevelThreeMapper extends EntityMapper<NickelStrunzLevelThreeDTO, NickelStrunzLevelThree> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    NickelStrunzLevelThreeDTO toDto(NickelStrunzLevelThree nickelStrunzLevelThree);

    @Mapping(source = "parentId", target = "parent")
    NickelStrunzLevelThree toEntity(NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO);

    default NickelStrunzLevelThree fromId(Long id) {
        if (id == null) {
            return null;
        }
        NickelStrunzLevelThree nickelStrunzLevelThree = new NickelStrunzLevelThree();
        nickelStrunzLevelThree.setId(id);
        return nickelStrunzLevelThree;
    }
}
