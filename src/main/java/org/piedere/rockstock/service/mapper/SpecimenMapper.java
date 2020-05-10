package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.SpecimenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specimen} and its DTO {@link SpecimenDTO}.
 */
@Mapper(componentModel = "spring", uses = {SpecimenStatusMapper.class, StorageLocationMapper.class, LocationMapper.class, MineralMapper.class})
public interface SpecimenMapper extends EntityMapper<SpecimenDTO, Specimen> {

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "status.name", target = "statusName")
    @Mapping(source = "storageLocation.id", target = "storageLocationId")
    @Mapping(source = "storageLocation.name", target = "storageLocationName")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.shortName", target = "locationShortName")
    SpecimenDTO toDto(Specimen specimen);

    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "storageLocationId", target = "storageLocation")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "removeMinerals", ignore = true)
    @Mapping(target = "series", ignore = true)
    @Mapping(target = "removeSeries", ignore = true)
    Specimen toEntity(SpecimenDTO specimenDTO);

    default Specimen fromId(Long id) {
        if (id == null) {
            return null;
        }
        Specimen specimen = new Specimen();
        specimen.setId(id);
        return specimen;
    }
}
