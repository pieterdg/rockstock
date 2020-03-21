package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.NickelStrunzLevelOneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelOne}.
 */
public interface NickelStrunzLevelOneService {

    /**
     * Save a nickelStrunzLevelOne.
     *
     * @param nickelStrunzLevelOneDTO the entity to save.
     * @return the persisted entity.
     */
    NickelStrunzLevelOneDTO save(NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO);

    /**
     * Get all the nickelStrunzLevelOnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NickelStrunzLevelOneDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nickelStrunzLevelOne.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NickelStrunzLevelOneDTO> findOne(Long id);

    /**
     * Delete the "id" nickelStrunzLevelOne.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
