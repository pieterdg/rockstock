package org.piedere.rockstock.service;

import org.piedere.rockstock.service.dto.SeriesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.piedere.rockstock.domain.Series}.
 */
public interface SeriesService {

    /**
     * Save a series.
     *
     * @param seriesDTO the entity to save.
     * @return the persisted entity.
     */
    SeriesDTO save(SeriesDTO seriesDTO);

    /**
     * Get all the series.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SeriesDTO> findAll(Pageable pageable);

    /**
     * Get all the series with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<SeriesDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" series.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SeriesDTO> findOne(Long id);

    /**
     * Delete the "id" series.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
