package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.NickelStrunzLevelTwo;
import org.piedere.rockstock.domain.NickelStrunzLevelOne;
import org.piedere.rockstock.repository.NickelStrunzLevelTwoRepository;
import org.piedere.rockstock.service.NickelStrunzLevelTwoService;
import org.piedere.rockstock.service.dto.NickelStrunzLevelTwoDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelTwoMapper;

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
 * Integration tests for the {@link NickelStrunzLevelTwoResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class NickelStrunzLevelTwoResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private NickelStrunzLevelTwoRepository nickelStrunzLevelTwoRepository;

    @Autowired
    private NickelStrunzLevelTwoMapper nickelStrunzLevelTwoMapper;

    @Autowired
    private NickelStrunzLevelTwoService nickelStrunzLevelTwoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNickelStrunzLevelTwoMockMvc;

    private NickelStrunzLevelTwo nickelStrunzLevelTwo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelTwo createEntity(EntityManager em) {
        NickelStrunzLevelTwo nickelStrunzLevelTwo = new NickelStrunzLevelTwo()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME);
        // Add required entity
        NickelStrunzLevelOne nickelStrunzLevelOne;
        if (TestUtil.findAll(em, NickelStrunzLevelOne.class).isEmpty()) {
            nickelStrunzLevelOne = NickelStrunzLevelOneResourceIT.createEntity(em);
            em.persist(nickelStrunzLevelOne);
            em.flush();
        } else {
            nickelStrunzLevelOne = TestUtil.findAll(em, NickelStrunzLevelOne.class).get(0);
        }
        nickelStrunzLevelTwo.setParent(nickelStrunzLevelOne);
        return nickelStrunzLevelTwo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelTwo createUpdatedEntity(EntityManager em) {
        NickelStrunzLevelTwo nickelStrunzLevelTwo = new NickelStrunzLevelTwo()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME);
        // Add required entity
        NickelStrunzLevelOne nickelStrunzLevelOne;
        if (TestUtil.findAll(em, NickelStrunzLevelOne.class).isEmpty()) {
            nickelStrunzLevelOne = NickelStrunzLevelOneResourceIT.createUpdatedEntity(em);
            em.persist(nickelStrunzLevelOne);
            em.flush();
        } else {
            nickelStrunzLevelOne = TestUtil.findAll(em, NickelStrunzLevelOne.class).get(0);
        }
        nickelStrunzLevelTwo.setParent(nickelStrunzLevelOne);
        return nickelStrunzLevelTwo;
    }

    @BeforeEach
    public void initTest() {
        nickelStrunzLevelTwo = createEntity(em);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelTwo() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelTwoRepository.findAll().size();

        // Create the NickelStrunzLevelTwo
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);
        restNickelStrunzLevelTwoMockMvc.perform(post("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isCreated());

        // Validate the NickelStrunzLevelTwo in the database
        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeCreate + 1);
        NickelStrunzLevelTwo testNickelStrunzLevelTwo = nickelStrunzLevelTwoList.get(nickelStrunzLevelTwoList.size() - 1);
        assertThat(testNickelStrunzLevelTwo.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNickelStrunzLevelTwo.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelTwoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelTwoRepository.findAll().size();

        // Create the NickelStrunzLevelTwo with an existing ID
        nickelStrunzLevelTwo.setId(1L);
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNickelStrunzLevelTwoMockMvc.perform(post("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelTwo in the database
        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelTwoRepository.findAll().size();
        // set the field null
        nickelStrunzLevelTwo.setCode(null);

        // Create the NickelStrunzLevelTwo, which fails.
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);

        restNickelStrunzLevelTwoMockMvc.perform(post("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelTwoRepository.findAll().size();
        // set the field null
        nickelStrunzLevelTwo.setName(null);

        // Create the NickelStrunzLevelTwo, which fails.
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);

        restNickelStrunzLevelTwoMockMvc.perform(post("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNickelStrunzLevelTwos() throws Exception {
        // Initialize the database
        nickelStrunzLevelTwoRepository.saveAndFlush(nickelStrunzLevelTwo);

        // Get all the nickelStrunzLevelTwoList
        restNickelStrunzLevelTwoMockMvc.perform(get("/api/nickel-strunz-level-twos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nickelStrunzLevelTwo.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getNickelStrunzLevelTwo() throws Exception {
        // Initialize the database
        nickelStrunzLevelTwoRepository.saveAndFlush(nickelStrunzLevelTwo);

        // Get the nickelStrunzLevelTwo
        restNickelStrunzLevelTwoMockMvc.perform(get("/api/nickel-strunz-level-twos/{id}", nickelStrunzLevelTwo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nickelStrunzLevelTwo.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingNickelStrunzLevelTwo() throws Exception {
        // Get the nickelStrunzLevelTwo
        restNickelStrunzLevelTwoMockMvc.perform(get("/api/nickel-strunz-level-twos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNickelStrunzLevelTwo() throws Exception {
        // Initialize the database
        nickelStrunzLevelTwoRepository.saveAndFlush(nickelStrunzLevelTwo);

        int databaseSizeBeforeUpdate = nickelStrunzLevelTwoRepository.findAll().size();

        // Update the nickelStrunzLevelTwo
        NickelStrunzLevelTwo updatedNickelStrunzLevelTwo = nickelStrunzLevelTwoRepository.findById(nickelStrunzLevelTwo.getId()).get();
        // Disconnect from session so that the updates on updatedNickelStrunzLevelTwo are not directly saved in db
        em.detach(updatedNickelStrunzLevelTwo);
        updatedNickelStrunzLevelTwo
            .code(UPDATED_CODE)
            .name(UPDATED_NAME);
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(updatedNickelStrunzLevelTwo);

        restNickelStrunzLevelTwoMockMvc.perform(put("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isOk());

        // Validate the NickelStrunzLevelTwo in the database
        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeUpdate);
        NickelStrunzLevelTwo testNickelStrunzLevelTwo = nickelStrunzLevelTwoList.get(nickelStrunzLevelTwoList.size() - 1);
        assertThat(testNickelStrunzLevelTwo.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNickelStrunzLevelTwo.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNickelStrunzLevelTwo() throws Exception {
        int databaseSizeBeforeUpdate = nickelStrunzLevelTwoRepository.findAll().size();

        // Create the NickelStrunzLevelTwo
        NickelStrunzLevelTwoDTO nickelStrunzLevelTwoDTO = nickelStrunzLevelTwoMapper.toDto(nickelStrunzLevelTwo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNickelStrunzLevelTwoMockMvc.perform(put("/api/nickel-strunz-level-twos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelTwoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelTwo in the database
        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNickelStrunzLevelTwo() throws Exception {
        // Initialize the database
        nickelStrunzLevelTwoRepository.saveAndFlush(nickelStrunzLevelTwo);

        int databaseSizeBeforeDelete = nickelStrunzLevelTwoRepository.findAll().size();

        // Delete the nickelStrunzLevelTwo
        restNickelStrunzLevelTwoMockMvc.perform(delete("/api/nickel-strunz-level-twos/{id}", nickelStrunzLevelTwo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NickelStrunzLevelTwo> nickelStrunzLevelTwoList = nickelStrunzLevelTwoRepository.findAll();
        assertThat(nickelStrunzLevelTwoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
