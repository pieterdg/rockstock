package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.SpecimenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.Specimen}.
 */
public interface SpecimenService {

    /**
     * Save a specimen.
     *
     * @param specimenDTO the entity to save.
     * @return the persisted entity.
     */
    SpecimenDTO save(SpecimenDTO specimenDTO);

    /**
     * Get all the specimen.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SpecimenDTO> findAll(Pageable pageable);

    /**
     * Get all the specimen with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<SpecimenDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" specimen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpecimenDTO> findOne(Long id);

    /**
     * Delete the "id" specimen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
