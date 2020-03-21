package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.NickelStrunzLevelOneService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.NickelStrunzLevelOneDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelOne}.
 */
@RestController
@RequestMapping("/api")
public class NickelStrunzLevelOneResource {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelOneResource.class);

    private static final String ENTITY_NAME = "nickelStrunzLevelOne";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NickelStrunzLevelOneService nickelStrunzLevelOneService;

    public NickelStrunzLevelOneResource(NickelStrunzLevelOneService nickelStrunzLevelOneService) {
        this.nickelStrunzLevelOneService = nickelStrunzLevelOneService;
    }

    /**
     * {@code POST  /nickel-strunz-level-ones} : Create a new nickelStrunzLevelOne.
     *
     * @param nickelStrunzLevelOneDTO the nickelStrunzLevelOneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nickelStrunzLevelOneDTO, or with status {@code 400 (Bad Request)} if the nickelStrunzLevelOne has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nickel-strunz-level-ones")
    public ResponseEntity<NickelStrunzLevelOneDTO> createNickelStrunzLevelOne(@Valid @RequestBody NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO) throws URISyntaxException {
        log.debug("REST request to save NickelStrunzLevelOne : {}", nickelStrunzLevelOneDTO);
        if (nickelStrunzLevelOneDTO.getId() != null) {
            throw new BadRequestAlertException("A new nickelStrunzLevelOne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NickelStrunzLevelOneDTO result = nickelStrunzLevelOneService.save(nickelStrunzLevelOneDTO);
        return ResponseEntity.created(new URI("/api/nickel-strunz-level-ones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nickel-strunz-level-ones} : Updates an existing nickelStrunzLevelOne.
     *
     * @param nickelStrunzLevelOneDTO the nickelStrunzLevelOneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nickelStrunzLevelOneDTO,
     * or with status {@code 400 (Bad Request)} if the nickelStrunzLevelOneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nickelStrunzLevelOneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nickel-strunz-level-ones")
    public ResponseEntity<NickelStrunzLevelOneDTO> updateNickelStrunzLevelOne(@Valid @RequestBody NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO) throws URISyntaxException {
        log.debug("REST request to update NickelStrunzLevelOne : {}", nickelStrunzLevelOneDTO);
        if (nickelStrunzLevelOneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NickelStrunzLevelOneDTO result = nickelStrunzLevelOneService.save(nickelStrunzLevelOneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nickelStrunzLevelOneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nickel-strunz-level-ones} : get all the nickelStrunzLevelOnes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nickelStrunzLevelOnes in body.
     */
    @GetMapping("/nickel-strunz-level-ones")
    public ResponseEntity<List<NickelStrunzLevelOneDTO>> getAllNickelStrunzLevelOnes(Pageable pageable) {
        log.debug("REST request to get a page of NickelStrunzLevelOnes");
        Page<NickelStrunzLevelOneDTO> page = nickelStrunzLevelOneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nickel-strunz-level-ones/:id} : get the "id" nickelStrunzLevelOne.
     *
     * @param id the id of the nickelStrunzLevelOneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nickelStrunzLevelOneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nickel-strunz-level-ones/{id}")
    public ResponseEntity<NickelStrunzLevelOneDTO> getNickelStrunzLevelOne(@PathVariable Long id) {
        log.debug("REST request to get NickelStrunzLevelOne : {}", id);
        Optional<NickelStrunzLevelOneDTO> nickelStrunzLevelOneDTO = nickelStrunzLevelOneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nickelStrunzLevelOneDTO);
    }

    /**
     * {@code DELETE  /nickel-strunz-level-ones/:id} : delete the "id" nickelStrunzLevelOne.
     *
     * @param id the id of the nickelStrunzLevelOneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nickel-strunz-level-ones/{id}")
    public ResponseEntity<Void> deleteNickelStrunzLevelOne(@PathVariable Long id) {
        log.debug("REST request to delete NickelStrunzLevelOne : {}", id);
        nickelStrunzLevelOneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
