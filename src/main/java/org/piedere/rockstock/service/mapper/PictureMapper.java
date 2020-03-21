package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.PictureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Picture} and its DTO {@link PictureDTO}.
 */
@Mapper(componentModel = "spring", uses = {SpecimenMapper.class, LocationMapper.class})
public interface PictureMapper extends EntityMapper<PictureDTO, Picture> {

    @Mapping(source = "specimen.id", target = "specimenId")
    @Mapping(source = "specimen.code", target = "specimenCode")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.shortName", target = "locationShortName")
    PictureDTO toDto(Picture picture);

    @Mapping(source = "specimenId", target = "specimen")
    @Mapping(source = "locationId", target = "location")
    Picture toEntity(PictureDTO pictureDTO);

    default Picture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setId(id);
        return picture;
    }
}
