package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.SpecimenStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SpecimenStatus} and its DTO {@link SpecimenStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SpecimenStatusMapper extends EntityMapper<SpecimenStatusDTO, SpecimenStatus> {



    default SpecimenStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        SpecimenStatus specimenStatus = new SpecimenStatus();
        specimenStatus.setId(id);
        return specimenStatus;
    }
}
