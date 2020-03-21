package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.NickelStrunzLevelOne;
import org.piedere.rockstock.repository.NickelStrunzLevelOneRepository;
import org.piedere.rockstock.service.NickelStrunzLevelOneService;
import org.piedere.rockstock.service.dto.NickelStrunzLevelOneDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelOneMapper;

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
 * Integration tests for the {@link NickelStrunzLevelOneResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class NickelStrunzLevelOneResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private NickelStrunzLevelOneRepository nickelStrunzLevelOneRepository;

    @Autowired
    private NickelStrunzLevelOneMapper nickelStrunzLevelOneMapper;

    @Autowired
    private NickelStrunzLevelOneService nickelStrunzLevelOneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNickelStrunzLevelOneMockMvc;

    private NickelStrunzLevelOne nickelStrunzLevelOne;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelOne createEntity(EntityManager em) {
        NickelStrunzLevelOne nickelStrunzLevelOne = new NickelStrunzLevelOne()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME);
        return nickelStrunzLevelOne;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelOne createUpdatedEntity(EntityManager em) {
        NickelStrunzLevelOne nickelStrunzLevelOne = new NickelStrunzLevelOne()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME);
        return nickelStrunzLevelOne;
    }

    @BeforeEach
    public void initTest() {
        nickelStrunzLevelOne = createEntity(em);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelOne() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelOneRepository.findAll().size();

        // Create the NickelStrunzLevelOne
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);
        restNickelStrunzLevelOneMockMvc.perform(post("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isCreated());

        // Validate the NickelStrunzLevelOne in the database
        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeCreate + 1);
        NickelStrunzLevelOne testNickelStrunzLevelOne = nickelStrunzLevelOneList.get(nickelStrunzLevelOneList.size() - 1);
        assertThat(testNickelStrunzLevelOne.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNickelStrunzLevelOne.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelOneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelOneRepository.findAll().size();

        // Create the NickelStrunzLevelOne with an existing ID
        nickelStrunzLevelOne.setId(1L);
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNickelStrunzLevelOneMockMvc.perform(post("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelOne in the database
        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelOneRepository.findAll().size();
        // set the field null
        nickelStrunzLevelOne.setCode(null);

        // Create the NickelStrunzLevelOne, which fails.
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);

        restNickelStrunzLevelOneMockMvc.perform(post("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelOneRepository.findAll().size();
        // set the field null
        nickelStrunzLevelOne.setName(null);

        // Create the NickelStrunzLevelOne, which fails.
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);

        restNickelStrunzLevelOneMockMvc.perform(post("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNickelStrunzLevelOnes() throws Exception {
        // Initialize the database
        nickelStrunzLevelOneRepository.saveAndFlush(nickelStrunzLevelOne);

        // Get all the nickelStrunzLevelOneList
        restNickelStrunzLevelOneMockMvc.perform(get("/api/nickel-strunz-level-ones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nickelStrunzLevelOne.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getNickelStrunzLevelOne() throws Exception {
        // Initialize the database
        nickelStrunzLevelOneRepository.saveAndFlush(nickelStrunzLevelOne);

        // Get the nickelStrunzLevelOne
        restNickelStrunzLevelOneMockMvc.perform(get("/api/nickel-strunz-level-ones/{id}", nickelStrunzLevelOne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nickelStrunzLevelOne.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingNickelStrunzLevelOne() throws Exception {
        // Get the nickelStrunzLevelOne
        restNickelStrunzLevelOneMockMvc.perform(get("/api/nickel-strunz-level-ones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNickelStrunzLevelOne() throws Exception {
        // Initialize the database
        nickelStrunzLevelOneRepository.saveAndFlush(nickelStrunzLevelOne);

        int databaseSizeBeforeUpdate = nickelStrunzLevelOneRepository.findAll().size();

        // Update the nickelStrunzLevelOne
        NickelStrunzLevelOne updatedNickelStrunzLevelOne = nickelStrunzLevelOneRepository.findById(nickelStrunzLevelOne.getId()).get();
        // Disconnect from session so that the updates on updatedNickelStrunzLevelOne are not directly saved in db
        em.detach(updatedNickelStrunzLevelOne);
        updatedNickelStrunzLevelOne
            .code(UPDATED_CODE)
            .name(UPDATED_NAME);
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(updatedNickelStrunzLevelOne);

        restNickelStrunzLevelOneMockMvc.perform(put("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isOk());

        // Validate the NickelStrunzLevelOne in the database
        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeUpdate);
        NickelStrunzLevelOne testNickelStrunzLevelOne = nickelStrunzLevelOneList.get(nickelStrunzLevelOneList.size() - 1);
        assertThat(testNickelStrunzLevelOne.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNickelStrunzLevelOne.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNickelStrunzLevelOne() throws Exception {
        int databaseSizeBeforeUpdate = nickelStrunzLevelOneRepository.findAll().size();

        // Create the NickelStrunzLevelOne
        NickelStrunzLevelOneDTO nickelStrunzLevelOneDTO = nickelStrunzLevelOneMapper.toDto(nickelStrunzLevelOne);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNickelStrunzLevelOneMockMvc.perform(put("/api/nickel-strunz-level-ones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelOneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelOne in the database
        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNickelStrunzLevelOne() throws Exception {
        // Initialize the database
        nickelStrunzLevelOneRepository.saveAndFlush(nickelStrunzLevelOne);

        int databaseSizeBeforeDelete = nickelStrunzLevelOneRepository.findAll().size();

        // Delete the nickelStrunzLevelOne
        restNickelStrunzLevelOneMockMvc.perform(delete("/api/nickel-strunz-level-ones/{id}", nickelStrunzLevelOne.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NickelStrunzLevelOne> nickelStrunzLevelOneList = nickelStrunzLevelOneRepository.findAll();
        assertThat(nickelStrunzLevelOneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
