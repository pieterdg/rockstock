package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.StorageLocationService;
import org.piedere.rockstock.domain.StorageLocation;
import org.piedere.rockstock.repository.StorageLocationRepository;
import org.piedere.rockstock.service.dto.StorageLocationDTO;
import org.piedere.rockstock.service.mapper.StorageLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StorageLocation}.
 */
@Service
@Transactional
public class StorageLocationServiceImpl implements StorageLocationService {

    private final Logger log = LoggerFactory.getLogger(StorageLocationServiceImpl.class);

    private final StorageLocationRepository storageLocationRepository;

    private final StorageLocationMapper storageLocationMapper;

    public StorageLocationServiceImpl(StorageLocationRepository storageLocationRepository, StorageLocationMapper storageLocationMapper) {
        this.storageLocationRepository = storageLocationRepository;
        this.storageLocationMapper = storageLocationMapper;
    }

    /**
     * Save a storageLocation.
     *
     * @param storageLocationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StorageLocationDTO save(StorageLocationDTO storageLocationDTO) {
        log.debug("Request to save StorageLocation : {}", storageLocationDTO);
        StorageLocation storageLocation = storageLocationMapper.toEntity(storageLocationDTO);
        storageLocation = storageLocationRepository.save(storageLocation);
        return storageLocationMapper.toDto(storageLocation);
    }

    /**
     * Get all the storageLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StorageLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StorageLocations");
        return storageLocationRepository.findAll(pageable)
            .map(storageLocationMapper::toDto);
    }

    /**
     * Get one storageLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StorageLocationDTO> findOne(Long id) {
        log.debug("Request to get StorageLocation : {}", id);
        return storageLocationRepository.findById(id)
            .map(storageLocationMapper::toDto);
    }

    /**
     * Delete the storageLocation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StorageLocation : {}", id);
        storageLocationRepository.deleteById(id);
    }
}
