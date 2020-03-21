package org.piedere.rockstock.service.impl;

import org.piedere.rockstock.service.PictureService;
import org.piedere.rockstock.domain.Picture;
import org.piedere.rockstock.repository.PictureRepository;
import org.piedere.rockstock.service.dto.PictureDTO;
import org.piedere.rockstock.service.mapper.PictureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Picture}.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final Logger log = LoggerFactory.getLogger(PictureServiceImpl.class);

    private final PictureRepository pictureRepository;

    private final PictureMapper pictureMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, PictureMapper pictureMapper) {
        this.pictureRepository = pictureRepository;
        this.pictureMapper = pictureMapper;
    }

    /**
     * Save a picture.
     *
     * @param pictureDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PictureDTO save(PictureDTO pictureDTO) {
        log.debug("Request to save Picture : {}", pictureDTO);
        Picture picture = pictureMapper.toEntity(pictureDTO);
        picture = pictureRepository.save(picture);
        return pictureMapper.toDto(picture);
    }

    /**
     * Get all the pictures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PictureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pictures");
        return pictureRepository.findAll(pageable)
            .map(pictureMapper::toDto);
    }

    /**
     * Get one picture by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PictureDTO> findOne(Long id) {
        log.debug("Request to get Picture : {}", id);
        return pictureRepository.findById(id)
            .map(pictureMapper::toDto);
    }

    /**
     * Delete the picture by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Picture : {}", id);
        pictureRepository.deleteById(id);
    }
}
