package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.SpecimenStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.SpecimenStatus}.
 */
public interface SpecimenStatusService {

    /**
     * Save a specimenStatus.
     *
     * @param specimenStatusDTO the entity to save.
     * @return the persisted entity.
     */
    SpecimenStatusDTO save(SpecimenStatusDTO specimenStatusDTO);

    /**
     * Get all the specimenStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SpecimenStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" specimenStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpecimenStatusDTO> findOne(Long id);

    /**
     * Delete the "id" specimenStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
