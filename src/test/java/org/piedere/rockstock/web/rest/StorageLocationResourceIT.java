package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.StorageLocation;
import org.piedere.rockstock.repository.StorageLocationRepository;
import org.piedere.rockstock.service.StorageLocationService;
import org.piedere.rockstock.service.dto.StorageLocationDTO;
import org.piedere.rockstock.service.mapper.StorageLocationMapper;

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
 * Integration tests for the {@link StorageLocationResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class StorageLocationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private StorageLocationRepository storageLocationRepository;

    @Autowired
    private StorageLocationMapper storageLocationMapper;

    @Autowired
    private StorageLocationService storageLocationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStorageLocationMockMvc;

    private StorageLocation storageLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StorageLocation createEntity(EntityManager em) {
        StorageLocation storageLocation = new StorageLocation()
            .name(DEFAULT_NAME);
        return storageLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StorageLocation createUpdatedEntity(EntityManager em) {
        StorageLocation storageLocation = new StorageLocation()
            .name(UPDATED_NAME);
        return storageLocation;
    }

    @BeforeEach
    public void initTest() {
        storageLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createStorageLocation() throws Exception {
        int databaseSizeBeforeCreate = storageLocationRepository.findAll().size();

        // Create the StorageLocation
        StorageLocationDTO storageLocationDTO = storageLocationMapper.toDto(storageLocation);
        restStorageLocationMockMvc.perform(post("/api/storage-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the StorageLocation in the database
        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeCreate + 1);
        StorageLocation testStorageLocation = storageLocationList.get(storageLocationList.size() - 1);
        assertThat(testStorageLocation.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createStorageLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = storageLocationRepository.findAll().size();

        // Create the StorageLocation with an existing ID
        storageLocation.setId(1L);
        StorageLocationDTO storageLocationDTO = storageLocationMapper.toDto(storageLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStorageLocationMockMvc.perform(post("/api/storage-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StorageLocation in the database
        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = storageLocationRepository.findAll().size();
        // set the field null
        storageLocation.setName(null);

        // Create the StorageLocation, which fails.
        StorageLocationDTO storageLocationDTO = storageLocationMapper.toDto(storageLocation);

        restStorageLocationMockMvc.perform(post("/api/storage-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageLocationDTO)))
            .andExpect(status().isBadRequest());

        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStorageLocations() throws Exception {
        // Initialize the database
        storageLocationRepository.saveAndFlush(storageLocation);

        // Get all the storageLocationList
        restStorageLocationMockMvc.perform(get("/api/storage-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(storageLocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getStorageLocation() throws Exception {
        // Initialize the database
        storageLocationRepository.saveAndFlush(storageLocation);

        // Get the storageLocation
        restStorageLocationMockMvc.perform(get("/api/storage-locations/{id}", storageLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(storageLocation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingStorageLocation() throws Exception {
        // Get the storageLocation
        restStorageLocationMockMvc.perform(get("/api/storage-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStorageLocation() throws Exception {
        // Initialize the database
        storageLocationRepository.saveAndFlush(storageLocation);

        int databaseSizeBeforeUpdate = storageLocationRepository.findAll().size();

        // Update the storageLocation
        StorageLocation updatedStorageLocation = storageLocationRepository.findById(storageLocation.getId()).get();
        // Disconnect from session so that the updates on updatedStorageLocation are not directly saved in db
        em.detach(updatedStorageLocation);
        updatedStorageLocation
            .name(UPDATED_NAME);
        StorageLocationDTO storageLocationDTO = storageLocationMapper.toDto(updatedStorageLocation);

        restStorageLocationMockMvc.perform(put("/api/storage-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageLocationDTO)))
            .andExpect(status().isOk());

        // Validate the StorageLocation in the database
        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeUpdate);
        StorageLocation testStorageLocation = storageLocationList.get(storageLocationList.size() - 1);
        assertThat(testStorageLocation.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingStorageLocation() throws Exception {
        int databaseSizeBeforeUpdate = storageLocationRepository.findAll().size();

        // Create the StorageLocation
        StorageLocationDTO storageLocationDTO = storageLocationMapper.toDto(storageLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStorageLocationMockMvc.perform(put("/api/storage-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StorageLocation in the database
        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStorageLocation() throws Exception {
        // Initialize the database
        storageLocationRepository.saveAndFlush(storageLocation);

        int databaseSizeBeforeDelete = storageLocationRepository.findAll().size();

        // Delete the storageLocation
        restStorageLocationMockMvc.perform(delete("/api/storage-locations/{id}", storageLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StorageLocation> storageLocationList = storageLocationRepository.findAll();
        assertThat(storageLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
