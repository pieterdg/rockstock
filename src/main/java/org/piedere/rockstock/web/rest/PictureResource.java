package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.PictureService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.PictureDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.piedere.rockstock.domain.Picture}.
 */
@RestController
@RequestMapping("/api")
public class PictureResource {

    private final Logger log = LoggerFactory.getLogger(PictureResource.class);

    private static final String ENTITY_NAME = "picture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PictureService pictureService;

    public PictureResource(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    /**
     * {@code POST  /pictures} : Create a new picture.
     *
     * @param pictureDTO the pictureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pictureDTO, or with status {@code 400 (Bad Request)} if the picture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pictures")
    public ResponseEntity<PictureDTO> createPicture(@Valid @RequestBody PictureDTO pictureDTO) throws URISyntaxException {
        log.debug("REST request to save Picture : {}", pictureDTO);
        if (pictureDTO.getId() != null) {
            throw new BadRequestAlertException("A new picture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PictureDTO result = pictureService.save(pictureDTO);
        return ResponseEntity.created(new URI("/api/pictures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pictures} : Updates an existing picture.
     *
     * @param pictureDTO the pictureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pictureDTO,
     * or with status {@code 400 (Bad Request)} if the pictureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pictureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pictures")
    public ResponseEntity<PictureDTO> updatePicture(@Valid @RequestBody PictureDTO pictureDTO) throws URISyntaxException {
        log.debug("REST request to update Picture : {}", pictureDTO);
        if (pictureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PictureDTO result = pictureService.save(pictureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pictureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pictures} : get all the pictures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pictures in body.
     */
    @GetMapping("/pictures")
    public ResponseEntity<List<PictureDTO>> getAllPictures(Pageable pageable) {
        log.debug("REST request to get a page of Pictures");
        Page<PictureDTO> page = pictureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pictures/:id} : get the "id" picture.
     *
     * @param id the id of the pictureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pictureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pictures/{id}")
    public ResponseEntity<PictureDTO> getPicture(@PathVariable Long id) {
        log.debug("REST request to get Picture : {}", id);
        Optional<PictureDTO> pictureDTO = pictureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pictureDTO);
    }

    /**
     * {@code DELETE  /pictures/:id} : delete the "id" picture.
     *
     * @param id the id of the pictureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pictures/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        log.debug("REST request to delete Picture : {}", id);
        pictureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
