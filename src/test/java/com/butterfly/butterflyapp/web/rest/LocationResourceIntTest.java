package com.butterfly.butterflyapp.web.rest;

import com.butterfly.butterflyapp.ButterflyApp;

import com.butterfly.butterflyapp.domain.Location;
import com.butterfly.butterflyapp.repository.LocationRepository;
import com.butterfly.butterflyapp.service.LocationService;
import com.butterfly.butterflyapp.service.dto.LocationDTO;
import com.butterfly.butterflyapp.service.mapper.LocationMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LocationResource REST controller.
 *
 * @see LocationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyApp.class)
public class LocationResourceIntTest {

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE_1 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_1 = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_1_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_1_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGE_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_2 = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGE_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_3 = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_3_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGE_4 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_4 = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_4_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_4_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGE_5 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_5 = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_5_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_5_CONTENT_TYPE = "image/png";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocationMockMvc;

    private Location location;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LocationResource locationResource = new LocationResource(locationService);
        this.restLocationMockMvc = MockMvcBuilders.standaloneSetup(locationResource)
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
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .locationName(DEFAULT_LOCATION_NAME)
            .image1(DEFAULT_IMAGE_1)
            .image1ContentType(DEFAULT_IMAGE_1_CONTENT_TYPE)
            .image2(DEFAULT_IMAGE_2)
            .image2ContentType(DEFAULT_IMAGE_2_CONTENT_TYPE)
            .image3(DEFAULT_IMAGE_3)
            .image3ContentType(DEFAULT_IMAGE_3_CONTENT_TYPE)
            .image4(DEFAULT_IMAGE_4)
            .image4ContentType(DEFAULT_IMAGE_4_CONTENT_TYPE)
            .image5(DEFAULT_IMAGE_5)
            .image5ContentType(DEFAULT_IMAGE_5_CONTENT_TYPE);
        return location;
    }

    @Before
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testLocation.getImage1()).isEqualTo(DEFAULT_IMAGE_1);
        assertThat(testLocation.getImage1ContentType()).isEqualTo(DEFAULT_IMAGE_1_CONTENT_TYPE);
        assertThat(testLocation.getImage2()).isEqualTo(DEFAULT_IMAGE_2);
        assertThat(testLocation.getImage2ContentType()).isEqualTo(DEFAULT_IMAGE_2_CONTENT_TYPE);
        assertThat(testLocation.getImage3()).isEqualTo(DEFAULT_IMAGE_3);
        assertThat(testLocation.getImage3ContentType()).isEqualTo(DEFAULT_IMAGE_3_CONTENT_TYPE);
        assertThat(testLocation.getImage4()).isEqualTo(DEFAULT_IMAGE_4);
        assertThat(testLocation.getImage4ContentType()).isEqualTo(DEFAULT_IMAGE_4_CONTENT_TYPE);
        assertThat(testLocation.getImage5()).isEqualTo(DEFAULT_IMAGE_5);
        assertThat(testLocation.getImage5ContentType()).isEqualTo(DEFAULT_IMAGE_5_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId(1L);
        LocationDTO locationDTO = locationMapper.toDto(location);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME.toString())))
            .andExpect(jsonPath("$.[*].image1ContentType").value(hasItem(DEFAULT_IMAGE_1_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image1").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_1))))
            .andExpect(jsonPath("$.[*].image2ContentType").value(hasItem(DEFAULT_IMAGE_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_2))))
            .andExpect(jsonPath("$.[*].image3ContentType").value(hasItem(DEFAULT_IMAGE_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image3").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_3))))
            .andExpect(jsonPath("$.[*].image4ContentType").value(hasItem(DEFAULT_IMAGE_4_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image4").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_4))))
            .andExpect(jsonPath("$.[*].image5ContentType").value(hasItem(DEFAULT_IMAGE_5_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image5").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_5))));
    }

    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME.toString()))
            .andExpect(jsonPath("$.image1ContentType").value(DEFAULT_IMAGE_1_CONTENT_TYPE))
            .andExpect(jsonPath("$.image1").value(Base64Utils.encodeToString(DEFAULT_IMAGE_1)))
            .andExpect(jsonPath("$.image2ContentType").value(DEFAULT_IMAGE_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.image2").value(Base64Utils.encodeToString(DEFAULT_IMAGE_2)))
            .andExpect(jsonPath("$.image3ContentType").value(DEFAULT_IMAGE_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.image3").value(Base64Utils.encodeToString(DEFAULT_IMAGE_3)))
            .andExpect(jsonPath("$.image4ContentType").value(DEFAULT_IMAGE_4_CONTENT_TYPE))
            .andExpect(jsonPath("$.image4").value(Base64Utils.encodeToString(DEFAULT_IMAGE_4)))
            .andExpect(jsonPath("$.image5ContentType").value(DEFAULT_IMAGE_5_CONTENT_TYPE))
            .andExpect(jsonPath("$.image5").value(Base64Utils.encodeToString(DEFAULT_IMAGE_5)));
    }

    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findOne(location.getId());
        updatedLocation
            .locationName(UPDATED_LOCATION_NAME)
            .image1(UPDATED_IMAGE_1)
            .image1ContentType(UPDATED_IMAGE_1_CONTENT_TYPE)
            .image2(UPDATED_IMAGE_2)
            .image2ContentType(UPDATED_IMAGE_2_CONTENT_TYPE)
            .image3(UPDATED_IMAGE_3)
            .image3ContentType(UPDATED_IMAGE_3_CONTENT_TYPE)
            .image4(UPDATED_IMAGE_4)
            .image4ContentType(UPDATED_IMAGE_4_CONTENT_TYPE)
            .image5(UPDATED_IMAGE_5)
            .image5ContentType(UPDATED_IMAGE_5_CONTENT_TYPE);
        LocationDTO locationDTO = locationMapper.toDto(updatedLocation);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testLocation.getImage1()).isEqualTo(UPDATED_IMAGE_1);
        assertThat(testLocation.getImage1ContentType()).isEqualTo(UPDATED_IMAGE_1_CONTENT_TYPE);
        assertThat(testLocation.getImage2()).isEqualTo(UPDATED_IMAGE_2);
        assertThat(testLocation.getImage2ContentType()).isEqualTo(UPDATED_IMAGE_2_CONTENT_TYPE);
        assertThat(testLocation.getImage3()).isEqualTo(UPDATED_IMAGE_3);
        assertThat(testLocation.getImage3ContentType()).isEqualTo(UPDATED_IMAGE_3_CONTENT_TYPE);
        assertThat(testLocation.getImage4()).isEqualTo(UPDATED_IMAGE_4);
        assertThat(testLocation.getImage4ContentType()).isEqualTo(UPDATED_IMAGE_4_CONTENT_TYPE);
        assertThat(testLocation.getImage5()).isEqualTo(UPDATED_IMAGE_5);
        assertThat(testLocation.getImage5ContentType()).isEqualTo(UPDATED_IMAGE_5_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);
        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Get the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Location.class);
        Location location1 = new Location();
        location1.setId(1L);
        Location location2 = new Location();
        location2.setId(location1.getId());
        assertThat(location1).isEqualTo(location2);
        location2.setId(2L);
        assertThat(location1).isNotEqualTo(location2);
        location1.setId(null);
        assertThat(location1).isNotEqualTo(location2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationDTO.class);
        LocationDTO locationDTO1 = new LocationDTO();
        locationDTO1.setId(1L);
        LocationDTO locationDTO2 = new LocationDTO();
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
        locationDTO2.setId(locationDTO1.getId());
        assertThat(locationDTO1).isEqualTo(locationDTO2);
        locationDTO2.setId(2L);
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
        locationDTO1.setId(null);
        assertThat(locationDTO1).isNotEqualTo(locationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(locationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(locationMapper.fromId(null)).isNull();
    }
}
