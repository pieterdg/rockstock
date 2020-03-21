package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.MineralService;
import org.piedere.rockstock.domain.Mineral;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.service.dto.MineralDTO;
import org.piedere.rockstock.service.mapper.MineralMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Mineral}.
 */
@Service
@Transactional
public class MineralServiceImpl implements MineralService {

    private final Logger log = LoggerFactory.getLogger(MineralServiceImpl.class);

    private final MineralRepository mineralRepository;

    private final MineralMapper mineralMapper;

    public MineralServiceImpl(MineralRepository mineralRepository, MineralMapper mineralMapper) {
        this.mineralRepository = mineralRepository;
        this.mineralMapper = mineralMapper;
    }

    /**
     * Save a mineral.
     *
     * @param mineralDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MineralDTO save(MineralDTO mineralDTO) {
        log.debug("Request to save Mineral : {}", mineralDTO);
        Mineral mineral = mineralMapper.toEntity(mineralDTO);
        mineral = mineralRepository.save(mineral);
        return mineralMapper.toDto(mineral);
    }

    /**
     * Get all the minerals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MineralDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Minerals");
        return mineralRepository.findAll(pageable)
            .map(mineralMapper::toDto);
    }

    /**
     * Get one mineral by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MineralDTO> findOne(Long id) {
        log.debug("Request to get Mineral : {}", id);
        return mineralRepository.findById(id)
            .map(mineralMapper::toDto);
    }

    /**
     * Delete the mineral by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mineral : {}", id);
        mineralRepository.deleteById(id);
    }
}
