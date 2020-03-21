package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.service.SeriesService;
import org.piedere.rockstock.web.rest.errors.BadRequestAlertException;
import org.piedere.rockstock.service.dto.SeriesDTO;

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
 * REST controller for managing {@link org.piedere.rockstock.domain.Series}.
 */
@RestController
@RequestMapping("/api")
public class SeriesResource {

    private final Logger log = LoggerFactory.getLogger(SeriesResource.class);

    private static final String ENTITY_NAME = "series";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeriesService seriesService;

    public SeriesResource(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    /**
     * {@code POST  /series} : Create a new series.
     *
     * @param seriesDTO the seriesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seriesDTO, or with status {@code 400 (Bad Request)} if the series has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/series")
    public ResponseEntity<SeriesDTO> createSeries(@Valid @RequestBody SeriesDTO seriesDTO) throws URISyntaxException {
        log.debug("REST request to save Series : {}", seriesDTO);
        if (seriesDTO.getId() != null) {
            throw new BadRequestAlertException("A new series cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeriesDTO result = seriesService.save(seriesDTO);
        return ResponseEntity.created(new URI("/api/series/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /series} : Updates an existing series.
     *
     * @param seriesDTO the seriesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seriesDTO,
     * or with status {@code 400 (Bad Request)} if the seriesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seriesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/series")
    public ResponseEntity<SeriesDTO> updateSeries(@Valid @RequestBody SeriesDTO seriesDTO) throws URISyntaxException {
        log.debug("REST request to update Series : {}", seriesDTO);
        if (seriesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SeriesDTO result = seriesService.save(seriesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, seriesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /series} : get all the series.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of series in body.
     */
    @GetMapping("/series")
    public ResponseEntity<List<SeriesDTO>> getAllSeries(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Series");
        Page<SeriesDTO> page;
        if (eagerload) {
            page = seriesService.findAllWithEagerRelationships(pageable);
        } else {
            page = seriesService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /series/:id} : get the "id" series.
     *
     * @param id the id of the seriesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seriesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/series/{id}")
    public ResponseEntity<SeriesDTO> getSeries(@PathVariable Long id) {
        log.debug("REST request to get Series : {}", id);
        Optional<SeriesDTO> seriesDTO = seriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(seriesDTO);
    }

    /**
     * {@code DELETE  /series/:id} : delete the "id" series.
     *
     * @param id the id of the seriesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/series/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        log.debug("REST request to delete Series : {}", id);
        seriesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
