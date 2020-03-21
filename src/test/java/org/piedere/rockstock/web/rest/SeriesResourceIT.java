package org.piedere.rockstock.web.rest;

import org.piedere.rockstock.RockstockApp;
import org.piedere.rockstock.domain.Series;
import org.piedere.rockstock.repository.SeriesRepository;
import org.piedere.rockstock.service.SeriesService;
import org.piedere.rockstock.service.dto.SeriesDTO;
import org.piedere.rockstock.service.mapper.SeriesMapper;
import org.piedere.rockstock.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.piedere.rockstock.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SeriesResource} REST controller.
 */
@SpringBootTest(classes = RockstockApp.class)
public class SeriesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SeriesRepository seriesRepository;

    @Mock
    private SeriesRepository seriesRepositoryMock;

    @Autowired
    private SeriesMapper seriesMapper;

    @Mock
    private SeriesService seriesServiceMock;

    @Autowired
    private SeriesService seriesService;

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

    private MockMvc restSeriesMockMvc;

    private Series series;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SeriesResource seriesResource = new SeriesResource(seriesService);
        this.restSeriesMockMvc = MockMvcBuilders.standaloneSetup(seriesResource)
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
    public static Series createEntity(EntityManager em) {
        Series series = new Series()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return series;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Series createUpdatedEntity(EntityManager em) {
        Series series = new Series()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return series;
    }

    @BeforeEach
    public void initTest() {
        series = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeries() throws Exception {
        int databaseSizeBeforeCreate = seriesRepository.findAll().size();

        // Create the Series
        SeriesDTO seriesDTO = seriesMapper.toDto(series);
        restSeriesMockMvc.perform(post("/api/series")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seriesDTO)))
            .andExpect(status().isCreated());

        // Validate the Series in the database
        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeCreate + 1);
        Series testSeries = seriesList.get(seriesList.size() - 1);
        assertThat(testSeries.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSeries.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSeriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = seriesRepository.findAll().size();

        // Create the Series with an existing ID
        series.setId(1L);
        SeriesDTO seriesDTO = seriesMapper.toDto(series);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeriesMockMvc.perform(post("/api/series")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Series in the database
        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = seriesRepository.findAll().size();
        // set the field null
        series.setName(null);

        // Create the Series, which fails.
        SeriesDTO seriesDTO = seriesMapper.toDto(series);

        restSeriesMockMvc.perform(post("/api/series")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seriesDTO)))
            .andExpect(status().isBadRequest());

        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSeries() throws Exception {
        // Initialize the database
        seriesRepository.saveAndFlush(series);

        // Get all the seriesList
        restSeriesMockMvc.perform(get("/api/series?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(series.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSeriesWithEagerRelationshipsIsEnabled() throws Exception {
        SeriesResource seriesResource = new SeriesResource(seriesServiceMock);
        when(seriesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSeriesMockMvc = MockMvcBuilders.standaloneSetup(seriesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSeriesMockMvc.perform(get("/api/series?eagerload=true"))
        .andExpect(status().isOk());

        verify(seriesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSeriesWithEagerRelationshipsIsNotEnabled() throws Exception {
        SeriesResource seriesResource = new SeriesResource(seriesServiceMock);
            when(seriesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSeriesMockMvc = MockMvcBuilders.standaloneSetup(seriesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSeriesMockMvc.perform(get("/api/series?eagerload=true"))
        .andExpect(status().isOk());

            verify(seriesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getSeries() throws Exception {
        // Initialize the database
        seriesRepository.saveAndFlush(series);

        // Get the series
        restSeriesMockMvc.perform(get("/api/series/{id}", series.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(series.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingSeries() throws Exception {
        // Get the series
        restSeriesMockMvc.perform(get("/api/series/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeries() throws Exception {
        // Initialize the database
        seriesRepository.saveAndFlush(series);

        int databaseSizeBeforeUpdate = seriesRepository.findAll().size();

        // Update the series
        Series updatedSeries = seriesRepository.findById(series.getId()).get();
        // Disconnect from session so that the updates on updatedSeries are not directly saved in db
        em.detach(updatedSeries);
        updatedSeries
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        SeriesDTO seriesDTO = seriesMapper.toDto(updatedSeries);

        restSeriesMockMvc.perform(put("/api/series")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seriesDTO)))
            .andExpect(status().isOk());

        // Validate the Series in the database
        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeUpdate);
        Series testSeries = seriesList.get(seriesList.size() - 1);
        assertThat(testSeries.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSeries.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSeries() throws Exception {
        int databaseSizeBeforeUpdate = seriesRepository.findAll().size();

        // Create the Series
        SeriesDTO seriesDTO = seriesMapper.toDto(series);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeriesMockMvc.perform(put("/api/series")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Series in the database
        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSeries() throws Exception {
        // Initialize the database
        seriesRepository.saveAndFlush(series);

        int databaseSizeBeforeDelete = seriesRepository.findAll().size();

        // Delete the series
        restSeriesMockMvc.perform(delete("/api/series/{id}", series.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
