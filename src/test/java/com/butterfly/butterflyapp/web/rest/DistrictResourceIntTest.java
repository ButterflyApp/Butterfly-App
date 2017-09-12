package com.butterfly.butterflyapp.web.rest;

import com.butterfly.butterflyapp.ButterflyApp;

import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.repository.DistrictRepository;
import com.butterfly.butterflyapp.service.DistrictService;
import com.butterfly.butterflyapp.service.dto.DistrictDTO;
import com.butterfly.butterflyapp.service.mapper.DistrictMapper;
import com.butterfly.butterflyapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DistrictResource REST controller.
 *
 * @see DistrictResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyApp.class)
public class DistrictResourceIntTest {

    private static final String DEFAULT_DISTRICT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT_NAME = "BBBBBBBBBB";

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDistrictMockMvc;

    private District district;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DistrictResource districtResource = new DistrictResource(districtService);
        this.restDistrictMockMvc = MockMvcBuilders.standaloneSetup(districtResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static District createEntity(EntityManager em) {
        District district = new District()
            .districtName(DEFAULT_DISTRICT_NAME);
        return district;
    }

    @Before
    public void initTest() {
        district = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistrict() throws Exception {
        int databaseSizeBeforeCreate = districtRepository.findAll().size();

        // Create the District
        DistrictDTO districtDTO = districtMapper.toDto(district);
        restDistrictMockMvc.perform(post("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isCreated());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeCreate + 1);
        District testDistrict = districtList.get(districtList.size() - 1);
        assertThat(testDistrict.getDistrictName()).isEqualTo(DEFAULT_DISTRICT_NAME);
    }

    @Test
    @Transactional
    public void createDistrictWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = districtRepository.findAll().size();

        // Create the District with an existing ID
        district.setId(1L);
        DistrictDTO districtDTO = districtMapper.toDto(district);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistrictMockMvc.perform(post("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDistricts() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get all the districtList
        restDistrictMockMvc.perform(get("/api/districts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(district.getId().intValue())))
            .andExpect(jsonPath("$.[*].districtName").value(hasItem(DEFAULT_DISTRICT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);

        // Get the district
        restDistrictMockMvc.perform(get("/api/districts/{id}", district.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(district.getId().intValue()))
            .andExpect(jsonPath("$.districtName").value(DEFAULT_DISTRICT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDistrict() throws Exception {
        // Get the district
        restDistrictMockMvc.perform(get("/api/districts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);
        int databaseSizeBeforeUpdate = districtRepository.findAll().size();

        // Update the district
        District updatedDistrict = districtRepository.findOne(district.getId());
        updatedDistrict
            .districtName(UPDATED_DISTRICT_NAME);
        DistrictDTO districtDTO = districtMapper.toDto(updatedDistrict);

        restDistrictMockMvc.perform(put("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isOk());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeUpdate);
        District testDistrict = districtList.get(districtList.size() - 1);
        assertThat(testDistrict.getDistrictName()).isEqualTo(UPDATED_DISTRICT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDistrict() throws Exception {
        int databaseSizeBeforeUpdate = districtRepository.findAll().size();

        // Create the District
        DistrictDTO districtDTO = districtMapper.toDto(district);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDistrictMockMvc.perform(put("/api/districts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(districtDTO)))
            .andExpect(status().isCreated());

        // Validate the District in the database
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDistrict() throws Exception {
        // Initialize the database
        districtRepository.saveAndFlush(district);
        int databaseSizeBeforeDelete = districtRepository.findAll().size();

        // Get the district
        restDistrictMockMvc.perform(delete("/api/districts/{id}", district.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<District> districtList = districtRepository.findAll();
        assertThat(districtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(District.class);
        District district1 = new District();
        district1.setId(1L);
        District district2 = new District();
        district2.setId(district1.getId());
        assertThat(district1).isEqualTo(district2);
        district2.setId(2L);
        assertThat(district1).isNotEqualTo(district2);
        district1.setId(null);
        assertThat(district1).isNotEqualTo(district2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistrictDTO.class);
        DistrictDTO districtDTO1 = new DistrictDTO();
        districtDTO1.setId(1L);
        DistrictDTO districtDTO2 = new DistrictDTO();
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
        districtDTO2.setId(districtDTO1.getId());
        assertThat(districtDTO1).isEqualTo(districtDTO2);
        districtDTO2.setId(2L);
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
        districtDTO1.setId(null);
        assertThat(districtDTO1).isNotEqualTo(districtDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(districtMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(districtMapper.fromId(null)).isNull();
    }
}
