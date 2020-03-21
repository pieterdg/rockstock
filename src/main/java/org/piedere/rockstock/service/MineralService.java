package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.MineralDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.Mineral}.
 */
public interface MineralService {

    /**
     * Save a mineral.
     *
     * @param mineralDTO the entity to save.
     * @return the persisted entity.
     */
    MineralDTO save(MineralDTO mineralDTO);

    /**
     * Get all the minerals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MineralDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mineral.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MineralDTO> findOne(Long id);

    /**
     * Delete the "id" mineral.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
