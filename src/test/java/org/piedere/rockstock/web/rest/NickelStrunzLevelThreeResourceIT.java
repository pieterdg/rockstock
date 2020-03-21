package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.NickelStrunzLevelThree;
import org.piedere.rockstock.domain.NickelStrunzLevelTwo;
import org.piedere.rockstock.repository.NickelStrunzLevelThreeRepository;
import org.piedere.rockstock.service.NickelStrunzLevelThreeService;
import org.piedere.rockstock.service.dto.NickelStrunzLevelThreeDTO;
import org.piedere.rockstock.service.mapper.NickelStrunzLevelThreeMapper;
import org.piedere.rockstock.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.piedere.rockstock.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NickelStrunzLevelThreeResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)
public class NickelStrunzLevelThreeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_NAME = "BBBBBBBBBB";

    @Autowired
    private NickelStrunzLevelThreeRepository nickelStrunzLevelThreeRepository;

    @Autowired
    private NickelStrunzLevelThreeMapper nickelStrunzLevelThreeMapper;

    @Autowired
    private NickelStrunzLevelThreeService nickelStrunzLevelThreeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNickelStrunzLevelThreeMockMvc;

    private NickelStrunzLevelThree nickelStrunzLevelThree;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NickelStrunzLevelThreeResource nickelStrunzLevelThreeResource = new NickelStrunzLevelThreeResource(nickelStrunzLevelThreeService);
        this.restNickelStrunzLevelThreeMockMvc = MockMvcBuilders.standaloneSetup(nickelStrunzLevelThreeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelThree createEntity(EntityManager em) {
        NickelStrunzLevelThree nickelStrunzLevelThree = new NickelStrunzLevelThree()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .shortName(DEFAULT_SHORT_NAME);
        // Add required entity
        NickelStrunzLevelTwo nickelStrunzLevelTwo;
        if (TestUtil.findAll(em, NickelStrunzLevelTwo.class).isEmpty()) {
            nickelStrunzLevelTwo = NickelStrunzLevelTwoResourceIT.createEntity(em);
            em.persist(nickelStrunzLevelTwo);
            em.flush();
        } else {
            nickelStrunzLevelTwo = TestUtil.findAll(em, NickelStrunzLevelTwo.class).get(0);
        }
        nickelStrunzLevelThree.setParent(nickelStrunzLevelTwo);
        return nickelStrunzLevelThree;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NickelStrunzLevelThree createUpdatedEntity(EntityManager em) {
        NickelStrunzLevelThree nickelStrunzLevelThree = new NickelStrunzLevelThree()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME);
        // Add required entity
        NickelStrunzLevelTwo nickelStrunzLevelTwo;
        if (TestUtil.findAll(em, NickelStrunzLevelTwo.class).isEmpty()) {
            nickelStrunzLevelTwo = NickelStrunzLevelTwoResourceIT.createUpdatedEntity(em);
            em.persist(nickelStrunzLevelTwo);
            em.flush();
        } else {
            nickelStrunzLevelTwo = TestUtil.findAll(em, NickelStrunzLevelTwo.class).get(0);
        }
        nickelStrunzLevelThree.setParent(nickelStrunzLevelTwo);
        return nickelStrunzLevelThree;
    }

    @BeforeEach
    public void initTest() {
        nickelStrunzLevelThree = createEntity(em);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelThree() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelThreeRepository.findAll().size();

        // Create the NickelStrunzLevelThree
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);
        restNickelStrunzLevelThreeMockMvc.perform(post("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isCreated());

        // Validate the NickelStrunzLevelThree in the database
        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeCreate + 1);
        NickelStrunzLevelThree testNickelStrunzLevelThree = nickelStrunzLevelThreeList.get(nickelStrunzLevelThreeList.size() - 1);
        assertThat(testNickelStrunzLevelThree.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNickelStrunzLevelThree.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNickelStrunzLevelThree.getShortName()).isEqualTo(DEFAULT_SHORT_NAME);
    }

    @Test
    @Transactional
    public void createNickelStrunzLevelThreeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nickelStrunzLevelThreeRepository.findAll().size();

        // Create the NickelStrunzLevelThree with an existing ID
        nickelStrunzLevelThree.setId(1L);
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNickelStrunzLevelThreeMockMvc.perform(post("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelThree in the database
        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelThreeRepository.findAll().size();
        // set the field null
        nickelStrunzLevelThree.setCode(null);

        // Create the NickelStrunzLevelThree, which fails.
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);

        restNickelStrunzLevelThreeMockMvc.perform(post("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = nickelStrunzLevelThreeRepository.findAll().size();
        // set the field null
        nickelStrunzLevelThree.setName(null);

        // Create the NickelStrunzLevelThree, which fails.
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);

        restNickelStrunzLevelThreeMockMvc.perform(post("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isBadRequest());

        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNickelStrunzLevelThrees() throws Exception {
        // Initialize the database
        nickelStrunzLevelThreeRepository.saveAndFlush(nickelStrunzLevelThree);

        // Get all the nickelStrunzLevelThreeList
        restNickelStrunzLevelThreeMockMvc.perform(get("/api/nickel-strunz-level-threes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nickelStrunzLevelThree.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shortName").value(hasItem(DEFAULT_SHORT_NAME)));
    }
    
    @Test
    @Transactional
    public void getNickelStrunzLevelThree() throws Exception {
        // Initialize the database
        nickelStrunzLevelThreeRepository.saveAndFlush(nickelStrunzLevelThree);

        // Get the nickelStrunzLevelThree
        restNickelStrunzLevelThreeMockMvc.perform(get("/api/nickel-strunz-level-threes/{id}", nickelStrunzLevelThree.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nickelStrunzLevelThree.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shortName").value(DEFAULT_SHORT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingNickelStrunzLevelThree() throws Exception {
        // Get the nickelStrunzLevelThree
        restNickelStrunzLevelThreeMockMvc.perform(get("/api/nickel-strunz-level-threes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNickelStrunzLevelThree() throws Exception {
        // Initialize the database
        nickelStrunzLevelThreeRepository.saveAndFlush(nickelStrunzLevelThree);

        int databaseSizeBeforeUpdate = nickelStrunzLevelThreeRepository.findAll().size();

        // Update the nickelStrunzLevelThree
        NickelStrunzLevelThree updatedNickelStrunzLevelThree = nickelStrunzLevelThreeRepository.findById(nickelStrunzLevelThree.getId()).get();
        // Disconnect from session so that the updates on updatedNickelStrunzLevelThree are not directly saved in db
        em.detach(updatedNickelStrunzLevelThree);
        updatedNickelStrunzLevelThree
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .shortName(UPDATED_SHORT_NAME);
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(updatedNickelStrunzLevelThree);

        restNickelStrunzLevelThreeMockMvc.perform(put("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isOk());

        // Validate the NickelStrunzLevelThree in the database
        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeUpdate);
        NickelStrunzLevelThree testNickelStrunzLevelThree = nickelStrunzLevelThreeList.get(nickelStrunzLevelThreeList.size() - 1);
        assertThat(testNickelStrunzLevelThree.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNickelStrunzLevelThree.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNickelStrunzLevelThree.getShortName()).isEqualTo(UPDATED_SHORT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingNickelStrunzLevelThree() throws Exception {
        int databaseSizeBeforeUpdate = nickelStrunzLevelThreeRepository.findAll().size();

        // Create the NickelStrunzLevelThree
        NickelStrunzLevelThreeDTO nickelStrunzLevelThreeDTO = nickelStrunzLevelThreeMapper.toDto(nickelStrunzLevelThree);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNickelStrunzLevelThreeMockMvc.perform(put("/api/nickel-strunz-level-threes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nickelStrunzLevelThreeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NickelStrunzLevelThree in the database
        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNickelStrunzLevelThree() throws Exception {
        // Initialize the database
        nickelStrunzLevelThreeRepository.saveAndFlush(nickelStrunzLevelThree);

        int databaseSizeBeforeDelete = nickelStrunzLevelThreeRepository.findAll().size();

        // Delete the nickelStrunzLevelThree
        restNickelStrunzLevelThreeMockMvc.perform(delete("/api/nickel-strunz-level-threes/{id}", nickelStrunzLevelThree.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NickelStrunzLevelThree> nickelStrunzLevelThreeList = nickelStrunzLevelThreeRepository.findAll();
        assertThat(nickelStrunzLevelThreeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
