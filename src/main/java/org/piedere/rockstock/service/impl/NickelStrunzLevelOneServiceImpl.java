package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.NickelStrunzLevelOneService;
import org.piedere.rockstock.domain.NickelStrunzLevelOne;
import org.piedere.rockstock.repository.NickelStrunzLevelOneRepository;
import org.piedere.rockstock.service.dto.NickelStrunzLevelOneDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelOneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NickelStrunzLevelOne}.
 */
@Service
@Transactional
public class NickelStrunzLevelOneServiceImpl implements NickelStrunzLevelOneService {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelOneServiceImpl.class);

    private final NickelStrunzLevelOneRepository nickelStrunzLevelOneRepository;

    private final NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper;

    public NickelStrunzLevelOneServiceImpl(NickelStrunzLevelOneRepository nickelStrunzLevelOneRepository, NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper) {
        this.nickelStrunzLevelOneRepository = nickelStrunzLevelOneRepository;
        this.nickelStrunzLevelOneMapper = nickelStrunzLevelOneMapper;
    }

    /**
     * Save a nickelStrunzLevelOne.
     *
     * @param nickelStrunzLevelOneDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NickelStrunzLevelOneDTO save(NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO) {
        log.debug("Request to save NickelStrunzLevelOne : {}", nickelStrunzLevelOneDTO);
        NickelStrunzLevelOne nickelStrunzLevelOne = nickelStrunzLevelOneMapper.toEntity(nickelStrunzLevelOneDTO);
        nickelStrunzLevelOne = nickelStrunzLevelOneRepository.save(nickelStrunzLevelOne);
        return nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);
    }

    /**
     * Get all the nickelStrunzLevelOnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NickelStrunzLevelOneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NickelStrunzLevelOnes");
        return nickelStrunzLevelOneRepository.findAll(pageable)
            .map(nickelStrunzLevelOneMapper::toDto);
    }

    /**
     * Get one nickelStrunzLevelOne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NickelStrunzLevelOneDTO> findOne(Long id) {
        log.debug("Request to get NickelStrunzLevelOne : {}", id);
        return nickelStrunzLevelOneRepository.findById(id)
            .map(nickelStrunzLevelOneMapper::toDto);
    }

    /**
     * Delete the nickelStrunzLevelOne by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NickelStrunzLevelOne : {}", id);
        nickelStrunzLevelOneRepository.deleteById(id);
    }
}
