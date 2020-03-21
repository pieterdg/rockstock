package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.SeriesService;
import org.piedere.rockstock.domain.Series;
import org.piedere.rockstock.repository.SeriesRepository;
import org.piedere.rockstock.service.dto.SeriesDTO;
import org.piedere.rockstock.service.mapper.SeriesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Series}.
 */
@Service
@Transactional
public class SeriesServiceImpl implements SeriesService {

    private final Logger log = LoggerFactory.getLogger(SeriesServiceImpl.class);

    private final SeriesRepository seriesRepository;

    private final SeriesMapper seriesMapper;

    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesMapper seriesMapper) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
    }

    /**
     * Save a series.
     *
     * @param seriesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SeriesDTO save(SeriesDTO seriesDTO) {
        log.debug("Request to save Series : {}", seriesDTO);
        Series series = seriesMapper.toEntity(seriesDTO);
        series = seriesRepository.save(series);
        return seriesMapper.toDto(series);
    }

    /**
     * Get all the series.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SeriesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Series");
        return seriesRepository.findAll(pageable)
            .map(seriesMapper::toDto);
    }

    /**
     * Get all the series with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<SeriesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return seriesRepository.findAllWithEagerRelationships(pageable).map(seriesMapper::toDto);
    }

    /**
     * Get one series by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SeriesDTO> findOne(Long id) {
        log.debug("Request to get Series : {}", id);
        return seriesRepository.findOneWithEagerRelationships(id)
            .map(seriesMapper::toDto);
    }

    /**
     * Delete the series by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Series : {}", id);
        seriesRepository.deleteById(id);
    }
}
