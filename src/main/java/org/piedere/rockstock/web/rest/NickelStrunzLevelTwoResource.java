package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.NickelStrunzLevelTwoService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelTwo}.
 */
@RestController
@RequestMapping("/api")
public class NickelStrunzLevelTwoResource {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelTwoResource.class);

    private static final String ENTITY_NAME = "nickelStrunzLevelTwo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NickelStrunzLevelTwoService nickelStrunzLevelTwoService;

    public NickelStrunzLevelTwoResource(NickelStrunzLevelTwoService nickelStrunzLevelTwoService) {
        this.nickelStrunzLevelTwoService = nickelStrunzLevelTwoService;
    }

    /**
     * {@code POST  /nickel-strunz-level-twos} : Create a new nickelStrunzLevelTwo.
     *
     * @param nickelStrunzLevelTwoDTO the nickelStrunzLevelTwoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nickelStrunzLevelTwoDTO, or with status {@code 400 (Bad Request)} if the nickelStrunzLevelTwo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nickel-strunz-level-twos")
    public ResponseEntity<NickelStrunzLevelTwoDTO> createNickelStrunzLevelTwo(@Valid @RequestBody NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO) throws URISyntaxException {
        log.debug("REST request to save NickelStrunzLevelTwo : {}", nickelStrunzLevelTwoDTO);
        if (nickelStrunzLevelTwoDTO.getId() != null) {
            throw new BadRequestAlertException("A new nickelStrunzLevelTwo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NickelStrunzLevelTwoDTO result = nickelStrunzLevelTwoService.save(nickelStrunzLevelTwoDTO);
        return ResponseEntity.created(new URI("/api/nickel-strunz-level-twos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nickel-strunz-level-twos} : Updates an existing nickelStrunzLevelTwo.
     *
     * @param nickelStrunzLevelTwoDTO the nickelStrunzLevelTwoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nickelStrunzLevelTwoDTO,
     * or with status {@code 400 (Bad Request)} if the nickelStrunzLevelTwoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nickelStrunzLevelTwoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nickel-strunz-level-twos")
    public ResponseEntity<NickelStrunzLevelTwoDTO> updateNickelStrunzLevelTwo(@Valid @RequestBody NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO) throws URISyntaxException {
        log.debug("REST request to update NickelStrunzLevelTwo : {}", nickelStrunzLevelTwoDTO);
        if (nickelStrunzLevelTwoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NickelStrunzLevelTwoDTO result = nickelStrunzLevelTwoService.save(nickelStrunzLevelTwoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nickelStrunzLevelTwoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nickel-strunz-level-twos} : get all the nickelStrunzLevelTwos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nickelStrunzLevelTwos in body.
     */
    @GetMapping("/nickel-strunz-level-twos")
    public ResponseEntity<List<NickelStrunzLevelTwoDTO>> getAllNickelStrunzLevelTwos(Pageable pageable) {
        log.debug("REST request to get a page of NickelStrunzLevelTwos");
        Page<NickelStrunzLevelTwoDTO> page = nickelStrunzLevelTwoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nickel-strunz-level-twos/:id} : get the "id" nickelStrunzLevelTwo.
     *
     * @param id the id of the nickelStrunzLevelTwoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nickelStrunzLevelTwoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nickel-strunz-level-twos/{id}")
    public ResponseEntity<NickelStrunzLevelTwoDTO> getNickelStrunzLevelTwo(@PathVariable Long id) {
        log.debug("REST request to get NickelStrunzLevelTwo : {}", id);
        Optional<NickelStrunzLevelTwoDTO> nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nickelStrunzLevelTwoDTO);
    }

    /**
     * {@code DELETE  /nickel-strunz-level-twos/:id} : delete the "id" nickelStrunzLevelTwo.
     *
     * @param id the id of the nickelStrunzLevelTwoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nickel-strunz-level-twos/{id}")
    public ResponseEntity<Void> deleteNickelStrunzLevelTwo(@PathVariable Long id) {
        log.debug("REST request to delete NickelStrunzLevelTwo : {}", id);
        nickelStrunzLevelTwoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
