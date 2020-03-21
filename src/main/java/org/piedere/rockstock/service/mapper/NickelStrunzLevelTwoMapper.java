package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NickelStrunzLevelTwo} and its DTO {@link NickelStrunzLevelTwoDTO}.
 */
@Mapper(componentModel = "spring", uses = {NickelStrunzLevelOneMapper.class})
public interface NickelStrunzLevelTwoMapper extends EntityMapper<NickelStrunzLevelTwoDTO, NickelStrunzLevelTwo> {

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    NickelStrunzLevelTwoDTO toDto(NickelStrunzLevelTwo nickelStrunzLevelTwo);

    @Mapping(source = "parentId", target = "parent")
    NickelStrunzLevelTwo toEntity(NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO);

    default NickelStrunzLevelTwo fromId(Long id) {
        if (id == null) {
            return null;
        }
        NickelStrunzLevelTwo nickelStrunzLevelTwo = new NickelStrunzLevelTwo();
        nickelStrunzLevelTwo.setId(id);
        return nickelStrunzLevelTwo;
    }
}
