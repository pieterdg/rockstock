package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.SpecimenStatus;
import org.piedere.rockstock.repository.SpecimenStatusRepository;
import org.piedere.rockstock.service.SpecimenStatusService;
import org.piedere.rockstock.service.dto.SpecimenStatusDTO;
import org.piedere.rockstock.service.mapper.SpecimenStatusMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SpecimenStatusResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SpecimenStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private SpecimenStatusRepository specimenStatusRepository;

    @Autowired
    private SpecimenStatusMapper specimenStatusMapper;

    @Autowired
    private SpecimenStatusService specimenStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecimenStatusMockMvc;

    private SpecimenStatus specimenStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpecimenStatus createEntity(EntityManager em) {
        SpecimenStatus specimenStatus = new SpecimenStatus()
            .name(DEFAULT_NAME);
        return specimenStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpecimenStatus createUpdatedEntity(EntityManager em) {
        SpecimenStatus specimenStatus = new SpecimenStatus()
            .name(UPDATED_NAME);
        return specimenStatus;
    }

    @BeforeEach
    public void initTest() {
        specimenStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecimenStatus() throws Exception {
        int databaseSizeBeforeCreate = specimenStatusRepository.findAll().size();

        // Create the SpecimenStatus
        SpecimenStatusDTO specimenStatusDTO = specimenStatusMapper.toDto(specimenStatus);
        restSpecimenStatusMockMvc.perform(post("/api/specimen-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the SpecimenStatus in the database
        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeCreate + 1);
        SpecimenStatus testSpecimenStatus = specimenStatusList.get(specimenStatusList.size() - 1);
        assertThat(testSpecimenStatus.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createSpecimenStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specimenStatusRepository.findAll().size();

        // Create the SpecimenStatus with an existing ID
        specimenStatus.setId(1L);
        SpecimenStatusDTO specimenStatusDTO = specimenStatusMapper.toDto(specimenStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecimenStatusMockMvc.perform(post("/api/specimen-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SpecimenStatus in the database
        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = specimenStatusRepository.findAll().size();
        // set the field null
        specimenStatus.setName(null);

        // Create the SpecimenStatus, which fails.
        SpecimenStatusDTO specimenStatusDTO = specimenStatusMapper.toDto(specimenStatus);

        restSpecimenStatusMockMvc.perform(post("/api/specimen-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenStatusDTO)))
            .andExpect(status().isBadRequest());

        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecimenStatuses() throws Exception {
        // Initialize the database
        specimenStatusRepository.saveAndFlush(specimenStatus);

        // Get all the specimenStatusList
        restSpecimenStatusMockMvc.perform(get("/api/specimen-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specimenStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getSpecimenStatus() throws Exception {
        // Initialize the database
        specimenStatusRepository.saveAndFlush(specimenStatus);

        // Get the specimenStatus
        restSpecimenStatusMockMvc.perform(get("/api/specimen-statuses/{id}", specimenStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specimenStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingSpecimenStatus() throws Exception {
        // Get the specimenStatus
        restSpecimenStatusMockMvc.perform(get("/api/specimen-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecimenStatus() throws Exception {
        // Initialize the database
        specimenStatusRepository.saveAndFlush(specimenStatus);

        int databaseSizeBeforeUpdate = specimenStatusRepository.findAll().size();

        // Update the specimenStatus
        SpecimenStatus updatedSpecimenStatus = specimenStatusRepository.findById(specimenStatus.getId()).get();
        // Disconnect from session so that the updates on updatedSpecimenStatus are not directly saved in db
        em.detach(updatedSpecimenStatus);
        updatedSpecimenStatus
            .name(UPDATED_NAME);
        SpecimenStatusDTO specimenStatusDTO = specimenStatusMapper.toDto(updatedSpecimenStatus);

        restSpecimenStatusMockMvc.perform(put("/api/specimen-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenStatusDTO)))
            .andExpect(status().isOk());

        // Validate the SpecimenStatus in the database
        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeUpdate);
        SpecimenStatus testSpecimenStatus = specimenStatusList.get(specimenStatusList.size() - 1);
        assertThat(testSpecimenStatus.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecimenStatus() throws Exception {
        int databaseSizeBeforeUpdate = specimenStatusRepository.findAll().size();

        // Create the SpecimenStatus
        SpecimenStatusDTO specimenStatusDTO = specimenStatusMapper.toDto(specimenStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecimenStatusMockMvc.perform(put("/api/specimen-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SpecimenStatus in the database
        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecimenStatus() throws Exception {
        // Initialize the database
        specimenStatusRepository.saveAndFlush(specimenStatus);

        int databaseSizeBeforeDelete = specimenStatusRepository.findAll().size();

        // Delete the specimenStatus
        restSpecimenStatusMockMvc.perform(delete("/api/specimen-statuses/{id}", specimenStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SpecimenStatus> specimenStatusList = specimenStatusRepository.findAll();
        assertThat(specimenStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
