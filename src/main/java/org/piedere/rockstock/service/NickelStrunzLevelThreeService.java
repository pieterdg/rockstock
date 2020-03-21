package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelThree}.
 */
public interface NickelStrunzLevelThreeService {

    /**
     * Save a nickelStrunzLevelThree.
     *
     * @param nickelStrunzLevelThreeDTO the entity to save.
     * @return the persisted entity.
     */
    NickelStrunzLevelThreeDTO save(NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO);

    /**
     * Get all the nickelStrunzLevelThrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NickelStrunzLevelThreeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" nickelStrunzLevelThree.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NickelStrunzLevelThreeDTO> findOne(Long id);

    /**
     * Delete the "id" nickelStrunzLevelThree.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
