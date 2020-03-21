package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.MineralService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.MineralDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.Mineral}.
 */
@RestController
@RequestMapping("/api")
public class MineralResource {

    private final Logger log = LoggerFactory.getLogger(MineralResource.class);

    private static final String ENTITY_NAME = "mineral";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MineralService mineralService;

    public MineralResource(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    /**
     * {@code POST  /minerals} : Create a new mineral.
     *
     * @param mineralDTO the mineralDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mineralDTO, or with status {@code 400 (Bad Request)} if the mineral has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/minerals")
    public ResponseEntity<MineralDTO> createMineral(@Valid @RequestBody MineralDTO mineralDTO) throws URISyntaxException {
        log.debug("REST request to save Mineral : {}", mineralDTO);
        if (mineralDTO.getId() != null) {
            throw new BadRequestAlertException("A new mineral cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MineralDTO result = mineralService.save(mineralDTO);
        return ResponseEntity.created(new URI("/api/minerals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /minerals} : Updates an existing mineral.
     *
     * @param mineralDTO the mineralDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mineralDTO,
     * or with status {@code 400 (Bad Request)} if the mineralDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mineralDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/minerals")
    public ResponseEntity<MineralDTO> updateMineral(@Valid @RequestBody MineralDTO mineralDTO) throws URISyntaxException {
        log.debug("REST request to update Mineral : {}", mineralDTO);
        if (mineralDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MineralDTO result = mineralService.save(mineralDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mineralDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /minerals} : get all the minerals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of minerals in body.
     */
    @GetMapping("/minerals")
    public ResponseEntity<List<MineralDTO>> getAllMinerals(Pageable pageable) {
        log.debug("REST request to get a page of Minerals");
        Page<MineralDTO> page = mineralService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /minerals/:id} : get the "id" mineral.
     *
     * @param id the id of the mineralDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mineralDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/minerals/{id}")
    public ResponseEntity<MineralDTO> getMineral(@PathVariable Long id) {
        log.debug("REST request to get Mineral : {}", id);
        Optional<MineralDTO> mineralDTO = mineralService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mineralDTO);
    }

    /**
     * {@code DELETE  /minerals/:id} : delete the "id" mineral.
     *
     * @param id the id of the mineralDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/minerals/{id}")
    public ResponseEntity<Void> deleteMineral(@PathVariable Long id) {
        log.debug("REST request to delete Mineral : {}", id);
        mineralService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
