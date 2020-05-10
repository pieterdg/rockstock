package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.StorageLocationService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.StorageLocationDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.StorageLocation}.
 */
@RestController
@RequestMapping("/api")
public class StorageLocationResource {

    private final Logger log = LoggerFactory.getLogger(StorageLocationResource.class);

    private static final String ENTITY_NAME = "storageLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StorageLocationService storageLocationService;

    public StorageLocationResource(StorageLocationService storageLocationService) {
        this.storageLocationService = storageLocationService;
    }

    /**
     * {@code POST  /storage-locations} : Create a new storageLocation.
     *
     * @param storageLocationDTO the storageLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new storageLocationDTO, or with status {@code 400 (Bad Request)} if the storageLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/storage-locations")
    public ResponseEntity<StorageLocationDTO> createStorageLocation(@Valid @RequestBody StorageLocationDTO storageLocationDTO) throws URISyntaxException {
        log.debug("REST request to save StorageLocation : {}", storageLocationDTO);
        if (storageLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new storageLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StorageLocationDTO result = storageLocationService.save(storageLocationDTO);
        return ResponseEntity.created(new URI("/api/storage-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /storage-locations} : Updates an existing storageLocation.
     *
     * @param storageLocationDTO the storageLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated storageLocationDTO,
     * or with status {@code 400 (Bad Request)} if the storageLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the storageLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/storage-locations")
    public ResponseEntity<StorageLocationDTO> updateStorageLocation(@Valid @RequestBody StorageLocationDTO storageLocationDTO) throws URISyntaxException {
        log.debug("REST request to update StorageLocation : {}", storageLocationDTO);
        if (storageLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StorageLocationDTO result = storageLocationService.save(storageLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, storageLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /storage-locations} : get all the storageLocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of storageLocations in body.
     */
    @GetMapping("/storage-locations")
    public ResponseEntity<List<StorageLocationDTO>> getAllStorageLocations(Pageable pageable) {
        log.debug("REST request to get a page of StorageLocations");
        Page<StorageLocationDTO> page = storageLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /storage-locations/:id} : get the "id" storageLocation.
     *
     * @param id the id of the storageLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the storageLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/storage-locations/{id}")
    public ResponseEntity<StorageLocationDTO> getStorageLocation(@PathVariable Long id) {
        log.debug("REST request to get StorageLocation : {}", id);
        Optional<StorageLocationDTO> storageLocationDTO = storageLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storageLocationDTO);
    }

    /**
     * {@code DELETE  /storage-locations/:id} : delete the "id" storageLocation.
     *
     * @param id the id of the storageLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/storage-locations/{id}")
    public ResponseEntity<Void> deleteStorageLocation(@PathVariable Long id) {
        log.debug("REST request to delete StorageLocation : {}", id);
        storageLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
