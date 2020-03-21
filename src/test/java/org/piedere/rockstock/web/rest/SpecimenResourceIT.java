package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.Specimen;
import org.piedere.rockstock.repository.SpecimenRepository;
import org.piedere.rockstock.service.SpecimenService;
import org.piedere.rockstock.service.dto.SpecimenDTO;
import org.piedere.rockstock.service.mapper.SpecimenMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SpecimenResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpecimenResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ACQUIRED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACQUIRED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ACQUIRED_AT = "AAAAAAAAAA";
    private static final String UPDATED_ACQUIRED_AT = "BBBBBBBBBB";

    private static final Float DEFAULT_ACQUIRED_PRICE = 1F;
    private static final Float UPDATED_ACQUIRED_PRICE = 2F;

    private static final String DEFAULT_ACQUIRED_BY = "AAAAAAAAAA";
    private static final String UPDATED_ACQUIRED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_ACQUIRED_FROM = "AAAAAAAAAA";
    private static final String UPDATED_ACQUIRED_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FLUORESCENT = false;
    private static final Boolean UPDATED_FLUORESCENT = true;

    @Autowired
    private SpecimenRepository specimenRepository;

    @Mock
    private SpecimenRepository specimenRepositoryMock;

    @Autowired
    private SpecimenMapper specimenMapper;

    @Mock
    private SpecimenService specimenServiceMock;

    @Autowired
    private SpecimenService specimenService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecimenMockMvc;

    private Specimen specimen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specimen createEntity(EntityManager em) {
        Specimen specimen = new Specimen()
            .code(DEFAULT_CODE)
            .acquiredDate(DEFAULT_ACQUIRED_DATE)
            .acquiredAt(DEFAULT_ACQUIRED_AT)
            .acquiredPrice(DEFAULT_ACQUIRED_PRICE)
            .acquiredBy(DEFAULT_ACQUIRED_BY)
            .acquiredFrom(DEFAULT_ACQUIRED_FROM)
            .remark(DEFAULT_REMARK)
            .fluorescent(DEFAULT_FLUORESCENT);
        return specimen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Specimen createUpdatedEntity(EntityManager em) {
        Specimen specimen = new Specimen()
            .code(UPDATED_CODE)
            .acquiredDate(UPDATED_ACQUIRED_DATE)
            .acquiredAt(UPDATED_ACQUIRED_AT)
            .acquiredPrice(UPDATED_ACQUIRED_PRICE)
            .acquiredBy(UPDATED_ACQUIRED_BY)
            .acquiredFrom(UPDATED_ACQUIRED_FROM)
            .remark(UPDATED_REMARK)
            .fluorescent(UPDATED_FLUORESCENT);
        return specimen;
    }

    @BeforeEach
    public void initTest() {
        specimen = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecimen() throws Exception {
        int databaseSizeBeforeCreate = specimenRepository.findAll().size();

        // Create the Specimen
        SpecimenDTO specimenDTO = specimenMapper.toDto(specimen);
        restSpecimenMockMvc.perform(post("/api/specimen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenDTO)))
            .andExpect(status().isCreated());

        // Validate the Specimen in the database
        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeCreate + 1);
        Specimen testSpecimen = specimenList.get(specimenList.size() - 1);
        assertThat(testSpecimen.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSpecimen.getAcquiredDate()).isEqualTo(DEFAULT_ACQUIRED_DATE);
        assertThat(testSpecimen.getAcquiredAt()).isEqualTo(DEFAULT_ACQUIRED_AT);
        assertThat(testSpecimen.getAcquiredPrice()).isEqualTo(DEFAULT_ACQUIRED_PRICE);
        assertThat(testSpecimen.getAcquiredBy()).isEqualTo(DEFAULT_ACQUIRED_BY);
        assertThat(testSpecimen.getAcquiredFrom()).isEqualTo(DEFAULT_ACQUIRED_FROM);
        assertThat(testSpecimen.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSpecimen.isFluorescent()).isEqualTo(DEFAULT_FLUORESCENT);
    }

    @Test
    @Transactional
    public void createSpecimenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specimenRepository.findAll().size();

        // Create the Specimen with an existing ID
        specimen.setId(1L);
        SpecimenDTO specimenDTO = specimenMapper.toDto(specimen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecimenMockMvc.perform(post("/api/specimen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specimen in the database
        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = specimenRepository.findAll().size();
        // set the field null
        specimen.setCode(null);

        // Create the Specimen, which fails.
        SpecimenDTO specimenDTO = specimenMapper.toDto(specimen);

        restSpecimenMockMvc.perform(post("/api/specimen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenDTO)))
            .andExpect(status().isBadRequest());

        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpecimen() throws Exception {
        // Initialize the database
        specimenRepository.saveAndFlush(specimen);

        // Get all the specimenList
        restSpecimenMockMvc.perform(get("/api/specimen?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specimen.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].acquiredDate").value(hasItem(DEFAULT_ACQUIRED_DATE.toString())))
            .andExpect(jsonPath("$.[*].acquiredAt").value(hasItem(DEFAULT_ACQUIRED_AT)))
            .andExpect(jsonPath("$.[*].acquiredPrice").value(hasItem(DEFAULT_ACQUIRED_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].acquiredBy").value(hasItem(DEFAULT_ACQUIRED_BY)))
            .andExpect(jsonPath("$.[*].acquiredFrom").value(hasItem(DEFAULT_ACQUIRED_FROM)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].fluorescent").value(hasItem(DEFAULT_FLUORESCENT.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSpecimenWithEagerRelationshipsIsEnabled() throws Exception {
        when(specimenServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSpecimenMockMvc.perform(get("/api/specimen?eagerload=true"))
            .andExpect(status().isOk());

        verify(specimenServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSpecimenWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(specimenServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSpecimenMockMvc.perform(get("/api/specimen?eagerload=true"))
            .andExpect(status().isOk());

        verify(specimenServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSpecimen() throws Exception {
        // Initialize the database
        specimenRepository.saveAndFlush(specimen);

        // Get the specimen
        restSpecimenMockMvc.perform(get("/api/specimen/{id}", specimen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specimen.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.acquiredDate").value(DEFAULT_ACQUIRED_DATE.toString()))
            .andExpect(jsonPath("$.acquiredAt").value(DEFAULT_ACQUIRED_AT))
            .andExpect(jsonPath("$.acquiredPrice").value(DEFAULT_ACQUIRED_PRICE.doubleValue()))
            .andExpect(jsonPath("$.acquiredBy").value(DEFAULT_ACQUIRED_BY))
            .andExpect(jsonPath("$.acquiredFrom").value(DEFAULT_ACQUIRED_FROM))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.fluorescent").value(DEFAULT_FLUORESCENT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSpecimen() throws Exception {
        // Get the specimen
        restSpecimenMockMvc.perform(get("/api/specimen/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecimen() throws Exception {
        // Initialize the database
        specimenRepository.saveAndFlush(specimen);

        int databaseSizeBeforeUpdate = specimenRepository.findAll().size();

        // Update the specimen
        Specimen updatedSpecimen = specimenRepository.findById(specimen.getId()).get();
        // Disconnect from session so that the updates on updatedSpecimen are not directly saved in db
        em.detach(updatedSpecimen);
        updatedSpecimen
            .code(UPDATED_CODE)
            .acquiredDate(UPDATED_ACQUIRED_DATE)
            .acquiredAt(UPDATED_ACQUIRED_AT)
            .acquiredPrice(UPDATED_ACQUIRED_PRICE)
            .acquiredBy(UPDATED_ACQUIRED_BY)
            .acquiredFrom(UPDATED_ACQUIRED_FROM)
            .remark(UPDATED_REMARK)
            .fluorescent(UPDATED_FLUORESCENT);
        SpecimenDTO specimenDTO = specimenMapper.toDto(updatedSpecimen);

        restSpecimenMockMvc.perform(put("/api/specimen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenDTO)))
            .andExpect(status().isOk());

        // Validate the Specimen in the database
        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeUpdate);
        Specimen testSpecimen = specimenList.get(specimenList.size() - 1);
        assertThat(testSpecimen.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSpecimen.getAcquiredDate()).isEqualTo(UPDATED_ACQUIRED_DATE);
        assertThat(testSpecimen.getAcquiredAt()).isEqualTo(UPDATED_ACQUIRED_AT);
        assertThat(testSpecimen.getAcquiredPrice()).isEqualTo(UPDATED_ACQUIRED_PRICE);
        assertThat(testSpecimen.getAcquiredBy()).isEqualTo(UPDATED_ACQUIRED_BY);
        assertThat(testSpecimen.getAcquiredFrom()).isEqualTo(UPDATED_ACQUIRED_FROM);
        assertThat(testSpecimen.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSpecimen.isFluorescent()).isEqualTo(UPDATED_FLUORESCENT);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecimen() throws Exception {
        int databaseSizeBeforeUpdate = specimenRepository.findAll().size();

        // Create the Specimen
        SpecimenDTO specimenDTO = specimenMapper.toDto(specimen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecimenMockMvc.perform(put("/api/specimen")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specimenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Specimen in the database
        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecimen() throws Exception {
        // Initialize the database
        specimenRepository.saveAndFlush(specimen);

        int databaseSizeBeforeDelete = specimenRepository.findAll().size();

        // Delete the specimen
        restSpecimenMockMvc.perform(delete("/api/specimen/{id}", specimen.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Specimen> specimenList = specimenRepository.findAll();
        assertThat(specimenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
