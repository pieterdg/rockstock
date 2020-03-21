package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.SpecimenStatusService;
import org.piedere.rockstock.domain.SpecimenStatus;
import org.piedere.rockstock.repository.SpecimenStatusRepository;
import org.piedere.rockstock.service.dto.SpecimenStatusDTO;
import org.piedere.rockstock.service.mapper.SpecimenStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SpecimenStatus}.
 */
@Service
@Transactional
public class SpecimenStatusServiceImpl implements SpecimenStatusService {

    private final Logger log = LoggerFactory.getLogger(SpecimenStatusServiceImpl.class);

    private final SpecimenStatusRepository specimenStatusRepository;

    private final SpecimenStatusMapper specimenStatusMapper;

    public SpecimenStatusServiceImpl(SpecimenStatusRepository specimenStatusRepository, SpecimenStatusMapper specimenStatusMapper) {
        this.specimenStatusRepository = specimenStatusRepository;
        this.specimenStatusMapper = specimenStatusMapper;
    }

    /**
     * Save a specimenStatus.
     *
     * @param specimenStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SpecimenStatusDTO save(SpecimenStatusDTO specimenStatusDTO) {
        log.debug("Request to save SpecimenStatus : {}", specimenStatusDTO);
        SpecimenStatus specimenStatus = specimenStatusMapper.toEntity(specimenStatusDTO);
        specimenStatus = specimenStatusRepository.save(specimenStatus);
        return specimenStatusMapper.toDto(specimenStatus);
    }

    /**
     * Get all the specimenStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecimenStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SpecimenStatuses");
        return specimenStatusRepository.findAll(pageable)
            .map(specimenStatusMapper::toDto);
    }

    /**
     * Get one specimenStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpecimenStatusDTO> findOne(Long id) {
        log.debug("Request to get SpecimenStatus : {}", id);
        return specimenStatusRepository.findById(id)
            .map(specimenStatusMapper::toDto);
    }

    /**
     * Delete the specimenStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SpecimenStatus : {}", id);
        specimenStatusRepository.deleteById(id);
    }
}
