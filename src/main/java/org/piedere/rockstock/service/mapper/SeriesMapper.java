package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.SeriesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Series} and its DTO {@link SeriesDTO}.
 */
@Mapper(componentModel = "spring", uses = {SpecimenMapper.class})
public interface SeriesMapper extends EntityMapper<SeriesDTO, Series> {


    @Mapping(target = "removeSpecimen", ignore = true)

    default Series fromId(Long id) {
        if (id == null) {
            return null;
        }
        Series series = new Series();
        series.setId(id);
        return series;
    }
}
