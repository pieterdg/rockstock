package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.SpecimenStatusService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.SpecimenStatusDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.SpecimenStatus}.
 */
@RestController
@RequestMapping("/api")
public class SpecimenStatusResource {

    private final Logger log = LoggerFactory.getLogger(SpecimenStatusResource.class);

    private static final String ENTITY_NAME = "specimenStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecimenStatusService specimenStatusService;

    public SpecimenStatusResource(SpecimenStatusService specimenStatusService) {
        this.specimenStatusService = specimenStatusService;
    }

    /**
     * {@code POST  /specimen-statuses} : Create a new specimenStatus.
     *
     * @param specimenStatusDTO the specimenStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specimenStatusDTO, or with status {@code 400 (Bad Request)} if the specimenStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specimen-statuses")
    public ResponseEntity<SpecimenStatusDTO> createSpecimenStatus(@Valid @RequestBody SpecimenStatusDTO specimenStatusDTO) throws URISyntaxException {
        log.debug("REST request to save SpecimenStatus : {}", specimenStatusDTO);
        if (specimenStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new specimenStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecimenStatusDTO result = specimenStatusService.save(specimenStatusDTO);
        return ResponseEntity.created(new URI("/api/specimen-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specimen-statuses} : Updates an existing specimenStatus.
     *
     * @param specimenStatusDTO the specimenStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specimenStatusDTO,
     * or with status {@code 400 (Bad Request)} if the specimenStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specimenStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specimen-statuses")
    public ResponseEntity<SpecimenStatusDTO> updateSpecimenStatus(@Valid @RequestBody SpecimenStatusDTO specimenStatusDTO) throws URISyntaxException {
        log.debug("REST request to update SpecimenStatus : {}", specimenStatusDTO);
        if (specimenStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecimenStatusDTO result = specimenStatusService.save(specimenStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specimenStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specimen-statuses} : get all the specimenStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specimenStatuses in body.
     */
    @GetMapping("/specimen-statuses")
    public ResponseEntity<List<SpecimenStatusDTO>> getAllSpecimenStatuses(Pageable pageable) {
        log.debug("REST request to get a page of SpecimenStatuses");
        Page<SpecimenStatusDTO> page = specimenStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specimen-statuses/:id} : get the "id" specimenStatus.
     *
     * @param id the id of the specimenStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specimenStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specimen-statuses/{id}")
    public ResponseEntity<SpecimenStatusDTO> getSpecimenStatus(@PathVariable Long id) {
        log.debug("REST request to get SpecimenStatus : {}", id);
        Optional<SpecimenStatusDTO> specimenStatusDTO = specimenStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specimenStatusDTO);
    }

    /**
     * {@code DELETE  /specimen-statuses/:id} : delete the "id" specimenStatus.
     *
     * @param id the id of the specimenStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specimen-statuses/{id}")
    public ResponseEntity<Void> deleteSpecimenStatus(@PathVariable Long id) {
        log.debug("REST request to delete SpecimenStatus : {}", id);
        specimenStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
