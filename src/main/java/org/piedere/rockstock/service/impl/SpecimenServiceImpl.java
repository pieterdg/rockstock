package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.SpecimenService;
import org.piedere.rockstock.domain.Specimen;
import org.piedere.rockstock.repository.SpecimenRepository;
import org.piedere.rockstock.service.dto.SpecimenDTO;
import org.piedere.rockstock.service.mapper.SpecimenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Specimen}.
 */
@Service
@Transactional
public class SpecimenServiceImpl implements SpecimenService {

    private final Logger log = LoggerFactory.getLogger(SpecimenServiceImpl.class);

    private final SpecimenRepository specimenRepository;

    private final SpecimenMapper specimenMapper;

    public SpecimenServiceImpl(SpecimenRepository specimenRepository, SpecimenMapper specimenMapper) {
        this.specimenRepository = specimenRepository;
        this.specimenMapper = specimenMapper;
    }

    /**
     * Save a specimen.
     *
     * @param specimenDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SpecimenDTO save(SpecimenDTO specimenDTO) {
        log.debug("Request to save Specimen : {}", specimenDTO);
        Specimen specimen = specimenMapper.toEntity(specimenDTO);
        specimen = specimenRepository.save(specimen);
        return specimenMapper.toDto(specimen);
    }

    /**
     * Get all the specimen.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecimenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Specimen");
        return specimenRepository.findAll(pageable)
            .map(specimenMapper::toDto);
    }

    /**
     * Get all the specimen with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<SpecimenDTO> findAllWithEagerRelationships(Pageable pageable) {
        return specimenRepository.findAllWithEagerRelationships(pageable).map(specimenMapper::toDto);
    }

    /**
     * Get one specimen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpecimenDTO> findOne(Long id) {
        log.debug("Request to get Specimen : {}", id);
        return specimenRepository.findOneWithEagerRelationships(id)
            .map(specimenMapper::toDto);
    }

    /**
     * Delete the specimen by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Specimen : {}", id);
        specimenRepository.deleteById(id);
    }
}
