package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelTwo}.
 */
public interface NickelStrunzLevelTwoService {

    /**
     * Save a nickelStrunzLevelTwo.
     *
     * @param nickelStrunzLevelTwoDTO the entity to save.
     * @return the persisted entity.
     */
    NickelStrunzLevelTwoDTO save(NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO);

    /**
     * Get all the nickelStrunzLevelTwos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NickelStrunzLevelTwoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nickelStrunzLevelTwo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NickelStrunzLevelTwoDTO> findOne(Long id);

    /**
     * Delete the "id" nickelStrunzLevelTwo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
