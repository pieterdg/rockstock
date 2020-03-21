package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.NickelStrunzLevelThreeService;
import org.piedere.rockstock.domain.NickelStrunzLevelThree;
import org.piedere.rockstock.repository.NickelStrunzLevelThreeRepository;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelThreeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NickelStrunzLevelThree}.
 */
@Service
@Transactional
public class NickelStrunzLevelThreeServiceImpl implements NickelStrunzLevelThreeService {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelThreeServiceImpl.class);

    private final NickelStrunzLevelThreeRepository nickelStrunzLevelThreeRepository;

    private final NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper;

    public NickelStrunzLevelThreeServiceImpl(NickelStrunzLevelThreeRepository nickelStrunzLevelThreeRepository, NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper) {
        this.nickelStrunzLevelThreeRepository = nickelStrunzLevelThreeRepository;
        this.nickelStrunzLevelThreeMapper = nickelStrunzLevelThreeMapper;
    }

    /**
     * Save a nickelStrunzLevelThree.
     *
     * @param nickelStrunzLevelThreeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NickelStrunzLevelThreeDTO save(NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO) {
        log.debug("Request to save NickelStrunzLevelThree : {}", nickelStrunzLevelThreeDTO);
        NickelStrunzLevelThree nickelStrunzLevelThree = nickelStrunzLevelThreeMapper.toEntity(nickelStrunzLevelThreeDTO);
        nickelStrunzLevelThree = nickelStrunzLevelThreeRepository.save(nickelStrunzLevelThree);
        return nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);
    }

    /**
     * Get all the nickelStrunzLevelThrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NickelStrunzLevelThreeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NickelStrunzLevelThrees");
        return nickelStrunzLevelThreeRepository.findAll(pageable)
            .map(nickelStrunzLevelThreeMapper::toDto);
    }

    /**
     * Get one nickelStrunzLevelThree by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NickelStrunzLevelThreeDTO> findOne(Long id) {
        log.debug("Request to get NickelStrunzLevelThree : {}", id);
        return nickelStrunzLevelThreeRepository.findById(id)
            .map(nickelStrunzLevelThreeMapper::toDto);
    }

    /**
     * Delete the nickelStrunzLevelThree by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NickelStrunzLevelThree : {}", id);
        nickelStrunzLevelThreeRepository.deleteById(id);
    }
}
