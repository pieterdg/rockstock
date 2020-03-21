package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.SpecimenService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.SpecimenDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.Specimen}.
 */
@RestController
@RequestMapping("/api")
public class SpecimenResource {

    private final Logger log = LoggerFactory.getLogger(SpecimenResource.class);

    private static final String ENTITY_NAME = "specimen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecimenService specimenService;

    public SpecimenResource(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }

    /**
     * {@code POST  /specimen} : Create a new specimen.
     *
     * @param specimenDTO the specimenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specimenDTO, or with status {@code 400 (Bad Request)} if the specimen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/specimen")
    public ResponseEntity<SpecimenDTO> createSpecimen(@Valid @RequestBody SpecimenDTO specimenDTO) throws URISyntaxException {
        log.debug("REST request to save Specimen : {}", specimenDTO);
        if (specimenDTO.getId() != null) {
            throw new BadRequestAlertException("A new specimen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecimenDTO result = specimenService.save(specimenDTO);
        return ResponseEntity.created(new URI("/api/specimen/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /specimen} : Updates an existing specimen.
     *
     * @param specimenDTO the specimenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specimenDTO,
     * or with status {@code 400 (Bad Request)} if the specimenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specimenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specimen")
    public ResponseEntity<SpecimenDTO> updateSpecimen(@Valid @RequestBody SpecimenDTO specimenDTO) throws URISyntaxException {
        log.debug("REST request to update Specimen : {}", specimenDTO);
        if (specimenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecimenDTO result = specimenService.save(specimenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specimenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /specimen} : get all the specimen.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specimen in body.
     */
    @GetMapping("/specimen")
    public ResponseEntity<List<SpecimenDTO>> getAllSpecimen(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Specimen");
        Page<SpecimenDTO> page;
        if (eagerload) {
            page = specimenService.findAllWithEagerRelationships(pageable);
        } else {
            page = specimenService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specimen/:id} : get the "id" specimen.
     *
     * @param id the id of the specimenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specimenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/specimen/{id}")
    public ResponseEntity<SpecimenDTO> getSpecimen(@PathVariable Long id) {
        log.debug("REST request to get Specimen : {}", id);
        Optional<SpecimenDTO> specimenDTO = specimenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specimenDTO);
    }

    /**
     * {@code DELETE  /specimen/:id} : delete the "id" specimen.
     *
     * @param id the id of the specimenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/specimen/{id}")
    public ResponseEntity<Void> deleteSpecimen(@PathVariable Long id) {
        log.debug("REST request to delete Specimen : {}", id);
        specimenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
