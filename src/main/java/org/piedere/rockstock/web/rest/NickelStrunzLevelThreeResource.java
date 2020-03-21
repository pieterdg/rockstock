package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.NickelStrunzLevelThreeService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.NickelStrunzLevelThree}.
 */
@RestController
@RequestMapping("/api")
public class NickelStrunzLevelThreeResource {

    private final Logger log = LoggerFactory.getLogger(NickelStrunzLevelThreeResource.class);

    private static final String ENTITY_NAME = "nickelStrunzLevelThree";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NickelStrunzLevelThreeService nickelStrunzLevelThreeService;

    public NickelStrunzLevelThreeResource(NickelStrunzLevelThreeService nickelStrunzLevelThreeService) {
        this.nickelStrunzLevelThreeService = nickelStrunzLevelThreeService;
    }

    /**
     * {@code POST  /nickel-strunz-level-threes} : Create a new nickelStrunzLevelThree.
     *
     * @param nickelStrunzLevelThreeDTO the nickelStrunzLevelThreeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nickelStrunzLevelThreeDTO, or with status {@code 400 (Bad Request)} if the nickelStrunzLevelThree has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nickel-strunz-level-threes")
    public ResponseEntity<NickelStrunzLevelThreeDTO> createNickelStrunzLevelThree(@Valid @RequestBody NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO) throws URISyntaxException {
        log.debug("REST request to save NickelStrunzLevelThree : {}", nickelStrunzLevelThreeDTO);
        if (nickelStrunzLevelThreeDTO.getId() != null) {
            throw new BadRequestAlertException("A new nickelStrunzLevelThree cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NickelStrunzLevelThreeDTO result = nickelStrunzLevelThreeService.save(nickelStrunzLevelThreeDTO);
        return ResponseEntity.created(new URI("/api/nickel-strunz-level-threes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nickel-strunz-level-threes} : Updates an existing nickelStrunzLevelThree.
     *
     * @param nickelStrunzLevelThreeDTO the nickelStrunzLevelThreeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nickelStrunzLevelThreeDTO,
     * or with status {@code 400 (Bad Request)} if the nickelStrunzLevelThreeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nickelStrunzLevelThreeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nickel-strunz-level-threes")
    public ResponseEntity<NickelStrunzLevelThreeDTO> updateNickelStrunzLevelThree(@Valid @RequestBody NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO) throws URISyntaxException {
        log.debug("REST request to update NickelStrunzLevelThree : {}", nickelStrunzLevelThreeDTO);
        if (nickelStrunzLevelThreeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NickelStrunzLevelThreeDTO result = nickelStrunzLevelThreeService.save(nickelStrunzLevelThreeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nickelStrunzLevelThreeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nickel-strunz-level-threes} : get all the nickelStrunzLevelThrees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nickelStrunzLevelThrees in body.
     */
    @GetMapping("/nickel-strunz-level-threes")
    public ResponseEntity<List<NickelStrunzLevelThreeDTO>> getAllNickelStrunzLevelThrees(Pageable pageable) {
        log.debug("REST request to get a page of NickelStrunzLevelThrees");
        Page<NickelStrunzLevelThreeDTO> page = nickelStrunzLevelThreeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nickel-strunz-level-threes/:id} : get the "id" nickelStrunzLevelThree.
     *
     * @param id the id of the nickelStrunzLevelThreeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nickelStrunzLevelThreeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nickel-strunz-level-threes/{id}")
    public ResponseEntity<NickelStrunzLevelThreeDTO> getNickelStrunzLevelThree(@PathVariable Long id) {
        log.debug("REST request to get NickelStrunzLevelThree : {}", id);
        Optional<NickelStrunzLevelThreeDTO> nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nickelStrunzLevelThreeDTO);
    }

    /**
     * {@code DELETE  /nickel-strunz-level-threes/:id} : delete the "id" nickelStrunzLevelThree.
     *
     * @param id the id of the nickelStrunzLevelThreeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nickel-strunz-level-threes/{id}")
    public ResponseEntity<Void> deleteNickelStrunzLevelThree(@PathVariable Long id) {
        log.debug("REST request to delete NickelStrunzLevelThree : {}", id);
        nickelStrunzLevelThreeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
