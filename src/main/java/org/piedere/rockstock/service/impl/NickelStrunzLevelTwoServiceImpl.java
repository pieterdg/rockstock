package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.NickelStrunzLevelTwoService;
import org.piedere.rockstock.domain.NickelStrunzLevelTwo;
import org.piedere.rockstock.repository.NickelStrunzLevelTwoRepository;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelTwoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NickelStrunzLevelTwo}.
 */
@Service
@Transactional
public class NickelStrunzLevelTwoServiceImpl implements NickelStrunzLevelTwoService {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelTwoServiceImpl.class);

    private final NickelStrunzLevelTwoRepository nickelStrunzLevelTwoRepository;

    private final NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper;

    public NickelStrunzLevelTwoServiceImpl(NickelStrunzLevelTwoRepository nickelStrunzLevelTwoRepository, NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper) {
        this.nickelStrunzLevelTwoRepository = nickelStrunzLevelTwoRepository;
        this.nickelStrunzLevelTwoMapper = nickelStrunzLevelTwoMapper;
    }

    /**
     * Save a nickelStrunzLevelTwo.
     *
     * @param nickelStrunzLevelTwoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NickelStrunzLevelTwoDTO save(NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO) {
        log.debug("Request to save NickelStrunzLevelTwo : {}", nickelStrunzLevelTwoDTO);
        NickelStrunzLevelTwo nickelStrunzLevelTwo = nickelStrunzLevelTwoMapper.toEntity(nickelStrunzLevelTwoDTO);
        nickelStrunzLevelTwo = nickelStrunzLevelTwoRepository.save(nickelStrunzLevelTwo);
        return nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);
    }

    /**
     * Get all the nickelStrunzLevelTwos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NickelStrunzLevelTwoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NickelStrunzLevelTwos");
        return nickelStrunzLevelTwoRepository.findAll(pageable)
            .map(nickelStrunzLevelTwoMapper::toDto);
    }

    /**
     * Get one nickelStrunzLevelTwo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NickelStrunzLevelTwoDTO> findOne(Long id) {
        log.debug("Request to get NickelStrunzLevelTwo : {}", id);
        return nickelStrunzLevelTwoRepository.findById(id)
            .map(nickelStrunzLevelTwoMapper::toDto);
    }

    /**
     * Delete the nickelStrunzLevelTwo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NickelStrunzLevelTwo : {}", id);
        nickelStrunzLevelTwoRepository.deleteById(id);
    }
}
