package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.Mineral;
import org.piedere.rockstock.domain.NickelStrunzLevelThree;
import org.piedere.rockstock.repository.MineralRepository;
import org.piedere.rockstock.service.MineralService;
import org.piedere.rockstock.service.dto.MineralDTO;
import org.piedere.rockstock.service.mapper.MineralMapper;
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
 * Integration tests for the {@link MineralResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)
public class MineralResourceIT {

    private static final String DEFAULT_DUTCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DUTCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULA = "AAAAAAAAAA";
    private static final String UPDATED_FORMULA = "BBBBBBBBBB";

    private static final Float DEFAULT_HARDNESS_MIN = 1F;
    private static final Float UPDATED_HARDNESS_MIN = 2F;

    private static final Float DEFAULT_HARDNESS_MAX = 1F;
    private static final Float UPDATED_HARDNESS_MAX = 2F;

    private static final String DEFAULT_CRYSTAL_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_CRYSTAL_SYSTEM = "BBBBBBBBBB";

    private static final String DEFAULT_NICKEL_STRUNTZ_LEVEL_FOUR = "AAAAAAAAAA";
    private static final String UPDATED_NICKEL_STRUNTZ_LEVEL_FOUR = "BBBBBBBBBB";

    private static final String DEFAULT_MINDAT_URL = "AAAAAAAAAA";
    private static final String UPDATED_MINDAT_URL = "BBBBBBBBBB";

    @Autowired
    private MineralRepository mineralRepository;

    @Autowired
    private MineralMapper mineralMapper;

    @Autowired
    private MineralService mineralService;

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

    private MockMvc restMineralMockMvc;

    private Mineral mineral;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MineralResource mineralResource = new MineralResource(mineralService);
        this.restMineralMockMvc = MockMvcBuilders.standaloneSetup(mineralResource)
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
    public static Mineral createEntity(EntityManager em) {
        Mineral mineral = new Mineral()
            .dutchName(DEFAULT_DUTCH_NAME)
            .formula(DEFAULT_FORMULA)
            .hardnessMin(DEFAULT_HARDNESS_MIN)
            .hardnessMax(DEFAULT_HARDNESS_MAX)
            .crystalSystem(DEFAULT_CRYSTAL_SYSTEM)
            .nickelStruntzLevelFour(DEFAULT_NICKEL_STRUNTZ_LEVEL_FOUR)
            .mindatUrl(DEFAULT_MINDAT_URL);
        // Add required entity
        NickelStrunzLevelThree nickelStrunzLevelThree;
        if (TestUtil.findAll(em, NickelStrunzLevelThree.class).isEmpty()) {
            nickelStrunzLevelThree = NickelStrunzLevelThreeResourceIT.createEntity(em);
            em.persist(nickelStrunzLevelThree);
            em.flush();
        } else {
            nickelStrunzLevelThree = TestUtil.findAll(em, NickelStrunzLevelThree.class).get(0);
        }
        mineral.setNickelStrunzLevelThree(nickelStrunzLevelThree);
        return mineral;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mineral createUpdatedEntity(EntityManager em) {
        Mineral mineral = new Mineral()
            .dutchName(UPDATED_DUTCH_NAME)
            .formula(UPDATED_FORMULA)
            .hardnessMin(UPDATED_HARDNESS_MIN)
            .hardnessMax(UPDATED_HARDNESS_MAX)
            .crystalSystem(UPDATED_CRYSTAL_SYSTEM)
            .nickelStruntzLevelFour(UPDATED_NICKEL_STRUNTZ_LEVEL_FOUR)
            .mindatUrl(UPDATED_MINDAT_URL);
        // Add required entity
        NickelStrunzLevelThree nickelStrunzLevelThree;
        if (TestUtil.findAll(em, NickelStrunzLevelThree.class).isEmpty()) {
            nickelStrunzLevelThree = NickelStrunzLevelThreeResourceIT.createUpdatedEntity(em);
            em.persist(nickelStrunzLevelThree);
            em.flush();
        } else {
            nickelStrunzLevelThree = TestUtil.findAll(em, NickelStrunzLevelThree.class).get(0);
        }
        mineral.setNickelStrunzLevelThree(nickelStrunzLevelThree);
        return mineral;
    }

    @BeforeEach
    public void initTest() {
        mineral = createEntity(em);
    }

    @Test
    @Transactional
    public void createMineral() throws Exception {
        int databaseSizeBeforeCreate = mineralRepository.findAll().size();

        // Create the Mineral
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);
        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isCreated());

        // Validate the Mineral in the database
        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeCreate + 1);
        Mineral testMineral = mineralList.get(mineralList.size() - 1);
        assertThat(testMineral.getDutchName()).isEqualTo(DEFAULT_DUTCH_NAME);
        assertThat(testMineral.getFormula()).isEqualTo(DEFAULT_FORMULA);
        assertThat(testMineral.getHardnessMin()).isEqualTo(DEFAULT_HARDNESS_MIN);
        assertThat(testMineral.getHardnessMax()).isEqualTo(DEFAULT_HARDNESS_MAX);
        assertThat(testMineral.getCrystalSystem()).isEqualTo(DEFAULT_CRYSTAL_SYSTEM);
        assertThat(testMineral.getNickelStruntzLevelFour()).isEqualTo(DEFAULT_NICKEL_STRUNTZ_LEVEL_FOUR);
        assertThat(testMineral.getMindatUrl()).isEqualTo(DEFAULT_MINDAT_URL);
    }

    @Test
    @Transactional
    public void createMineralWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mineralRepository.findAll().size();

        // Create the Mineral with an existing ID
        mineral.setId(1L);
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mineral in the database
        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDutchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setDutchName(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFormulaIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setFormula(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHardnessMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setHardnessMin(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHardnessMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setHardnessMax(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCrystalSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setCrystalSystem(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNickelStruntzLevelFourIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setNickelStruntzLevelFour(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMindatUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = mineralRepository.findAll().size();
        // set the field null
        mineral.setMindatUrl(null);

        // Create the Mineral, which fails.
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        restMineralMockMvc.perform(post("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMinerals() throws Exception {
        // Initialize the database
        mineralRepository.saveAndFlush(mineral);

        // Get all the mineralList
        restMineralMockMvc.perform(get("/api/minerals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mineral.getId().intValue())))
            .andExpect(jsonPath("$.[*].dutchName").value(hasItem(DEFAULT_DUTCH_NAME)))
            .andExpect(jsonPath("$.[*].formula").value(hasItem(DEFAULT_FORMULA)))
            .andExpect(jsonPath("$.[*].hardnessMin").value(hasItem(DEFAULT_HARDNESS_MIN.doubleValue())))
            .andExpect(jsonPath("$.[*].hardnessMax").value(hasItem(DEFAULT_HARDNESS_MAX.doubleValue())))
            .andExpect(jsonPath("$.[*].crystalSystem").value(hasItem(DEFAULT_CRYSTAL_SYSTEM)))
            .andExpect(jsonPath("$.[*].nickelStruntzLevelFour").value(hasItem(DEFAULT_NICKEL_STRUNTZ_LEVEL_FOUR)))
            .andExpect(jsonPath("$.[*].mindatUrl").value(hasItem(DEFAULT_MINDAT_URL)));
    }
    
    @Test
    @Transactional
    public void getMineral() throws Exception {
        // Initialize the database
        mineralRepository.saveAndFlush(mineral);

        // Get the mineral
        restMineralMockMvc.perform(get("/api/minerals/{id}", mineral.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mineral.getId().intValue()))
            .andExpect(jsonPath("$.dutchName").value(DEFAULT_DUTCH_NAME))
            .andExpect(jsonPath("$.formula").value(DEFAULT_FORMULA))
            .andExpect(jsonPath("$.hardnessMin").value(DEFAULT_HARDNESS_MIN.doubleValue()))
            .andExpect(jsonPath("$.hardnessMax").value(DEFAULT_HARDNESS_MAX.doubleValue()))
            .andExpect(jsonPath("$.crystalSystem").value(DEFAULT_CRYSTAL_SYSTEM))
            .andExpect(jsonPath("$.nickelStruntzLevelFour").value(DEFAULT_NICKEL_STRUNTZ_LEVEL_FOUR))
            .andExpect(jsonPath("$.mindatUrl").value(DEFAULT_MINDAT_URL));
    }

    @Test
    @Transactional
    public void getNonExistingMineral() throws Exception {
        // Get the mineral
        restMineralMockMvc.perform(get("/api/minerals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMineral() throws Exception {
        // Initialize the database
        mineralRepository.saveAndFlush(mineral);

        int databaseSizeBeforeUpdate = mineralRepository.findAll().size();

        // Update the mineral
        Mineral updatedMineral = mineralRepository.findById(mineral.getId()).get();
        // Disconnect from session so that the updates on updatedMineral are not directly saved in db
        em.detach(updatedMineral);
        updatedMineral
            .dutchName(UPDATED_DUTCH_NAME)
            .formula(UPDATED_FORMULA)
            .hardnessMin(UPDATED_HARDNESS_MIN)
            .hardnessMax(UPDATED_HARDNESS_MAX)
            .crystalSystem(UPDATED_CRYSTAL_SYSTEM)
            .nickelStruntzLevelFour(UPDATED_NICKEL_STRUNTZ_LEVEL_FOUR)
            .mindatUrl(UPDATED_MINDAT_URL);
        MineralDTO mineralDTO = mineralMapper.toDto(updatedMineral);

        restMineralMockMvc.perform(put("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isOk());

        // Validate the Mineral in the database
        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeUpdate);
        Mineral testMineral = mineralList.get(mineralList.size() - 1);
        assertThat(testMineral.getDutchName()).isEqualTo(UPDATED_DUTCH_NAME);
        assertThat(testMineral.getFormula()).isEqualTo(UPDATED_FORMULA);
        assertThat(testMineral.getHardnessMin()).isEqualTo(UPDATED_HARDNESS_MIN);
        assertThat(testMineral.getHardnessMax()).isEqualTo(UPDATED_HARDNESS_MAX);
        assertThat(testMineral.getCrystalSystem()).isEqualTo(UPDATED_CRYSTAL_SYSTEM);
        assertThat(testMineral.getNickelStruntzLevelFour()).isEqualTo(UPDATED_NICKEL_STRUNTZ_LEVEL_FOUR);
        assertThat(testMineral.getMindatUrl()).isEqualTo(UPDATED_MINDAT_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingMineral() throws Exception {
        int databaseSizeBeforeUpdate = mineralRepository.findAll().size();

        // Create the Mineral
        MineralDTO mineralDTO = mineralMapper.toDto(mineral);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMineralMockMvc.perform(put("/api/minerals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mineralDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mineral in the database
        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMineral() throws Exception {
        // Initialize the database
        mineralRepository.saveAndFlush(mineral);

        int databaseSizeBeforeDelete = mineralRepository.findAll().size();

        // Delete the mineral
        restMineralMockMvc.perform(delete("/api/minerals/{id}", mineral.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mineral> mineralList = mineralRepository.findAll();
        assertThat(mineralList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
