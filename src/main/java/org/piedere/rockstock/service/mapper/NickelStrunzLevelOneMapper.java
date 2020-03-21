package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.NickelStrunzLevelOneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NickelStrunzLevelOne} and its DTO {@link NickelStrunzLevelOneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NickelStrunzLevelOneMapper extends EntityMapper<NickelStrunzLevelOneDTO, NickelStrunzLevelOne> {



    default NickelStrunzLevelOne fromId(Long id) {
        if (id == null) {
            return null;
        }
        NickelStrunzLevelOne nickelStrunzLevelOne = new NickelStrunzLevelOne();
        nickelStrunzLevelOne.setId(id);
        return nickelStrunzLevelOne;
    }
}
