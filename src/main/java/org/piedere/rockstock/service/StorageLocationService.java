package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.StorageLocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.StorageLocation}.
 */
public interface StorageLocationService {

    /**
     * Save a storageLocation.
     *
     * @param storageLocationDTO the entity to save.
     * @return the persisted entity.
     */
    StorageLocationDTO save(StorageLocationDTO storageLocationDTO);

    /**
     * Get all the storageLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StorageLocationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" storageLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StorageLocationDTO> findOne(Long id);

    /**
     * Delete the "id" storageLocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
