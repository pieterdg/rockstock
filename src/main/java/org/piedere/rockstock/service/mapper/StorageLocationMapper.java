package org.piedere.rockstock.service.mapper;


import org.piedere.rockstock.domain.*;
import org.piedere.rockstock.service.dto.StorageLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StorageLocation} and its DTO {@link StorageLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StorageLocationMapper extends EntityMapper<StorageLocationDTO, StorageLocation> {



    default StorageLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        StorageLocation storageLocation = new StorageLocation();
        storageLocation.setId(id);
        return storageLocation;
    }
}
