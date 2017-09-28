package com.butterfly.butterflyapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.butterfly.butterflyapp.domain.Country;
import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.domain.Location;
import com.butterfly.butterflyapp.domain.State;
import com.butterfly.butterflyapp.domain.StateMediator;
import com.butterfly.butterflyapp.domain.User;
import com.butterfly.butterflyapp.repository.CountryRepository;
import com.butterfly.butterflyapp.service.CountryService;
import com.butterfly.butterflyapp.web.rest.util.HeaderUtil;
import com.butterfly.butterflyapp.service.dto.CountryDTO;
import com.butterfly.butterflyapp.service.dto.DistrictDTO;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

	private final Logger log = LoggerFactory.getLogger(CountryResource.class);

	private static final String ENTITY_NAME = "country";

	private final CountryService countryService;

	public CountryResource(CountryService countryService) {
		this.countryService = countryService;
	}

	/**
	 * POST /countries : Create a new country.
	 *
	 * @param countryDTO
	 *            the countryDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         countryDTO, or with status 400 (Bad Request) if the country has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/countries")
	@Timed
	public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
		log.debug("REST request to save Country : {}", countryDTO);
		if (countryDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new country cannot already have an ID"))
					.body(null);
		}
		CountryDTO result = countryService.save(countryDTO);
		return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /countries : Updates an existing country.
	 *
	 * @param countryDTO
	 *            the countryDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         countryDTO, or with status 400 (Bad Request) if the countryDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the countryDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/countries")
	@Timed
	public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
		log.debug("REST request to update Country : {}", countryDTO);
		if (countryDTO.getId() == null) {
			return createCountry(countryDTO);
		}
		CountryDTO result = countryService.save(countryDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryDTO.getId().toString())).body(result);
	}

	/**
	 * GET /countries : get all the countries.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of countries in
	 *         body
	 */
	@GetMapping("/countries")
	@Timed
	public List<CountryDTO> getAllCountries() {
		log.debug("REST request to get all Countries");
		return countryService.findAll();
	}

	/**
	 * GET /countries/:id : get the "id" country.
	 *
	 * @param id
	 *            the id of the countryDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the countryDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/countries/{id}")
	@Timed
	public ResponseEntity<CountryDTO> getCountry(@PathVariable Long id) {
		log.debug("REST request to get Country : {}", id);
		CountryDTO countryDTO = countryService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(countryDTO));
	}

	/**
	 * DELETE /countries/:id : delete the "id" country.
	 *
	 * @param id
	 *            the id of the countryDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/countries/{id}")
	@Timed
	public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
		log.debug("REST request to delete Country : {}", id);
		countryService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@Autowired
	CountryRepository c;

	@GetMapping("/tes/{id}")
	public Country test(@PathVariable long id) {

		return c.findOne(id);
	}

	@GetMapping("/countries/all")
	public List<StateMediator> allCountries() {
		List<Country> list = countryService.findAllCountries();

		List<StateMediator> stateMediator = new ArrayList<StateMediator>();

		for (Country country : list) {
			Set<State> states = country.getStates();

			for (State state : states) {
				StateMediator sm = new StateMediator();
				List<Location> stateLocations = new ArrayList<Location>();
				long locationCount = 0;
				sm.setId(state.getId());
				sm.setName(state.getStateName());

				Set<District> districts = state.getDistricts();
				sm.setDistrictCount(districts.size());

				for (District district : districts) {
					Set<Location> locations = district.getLocations();
					for (Location loc : locations) {
						stateLocations.add(loc);
					}
					locationCount = locationCount + locations.size();

				}

				if (stateLocations.size() >= 3) {

					int s = (int) (Math.random() * stateLocations.size());
					sm.setImage1(stateLocations.get(s).getImage1());
					sm.setImage1ContentType(stateLocations.get(s).getImage1ContentType());

					int s2 = (int) (Math.random() * stateLocations.size());
					sm.setImage2(stateLocations.get(s2).getImage2());
					sm.setImage2ContentType(stateLocations.get(s2).getImage2ContentType());

					int s3 = (int) (Math.random() * stateLocations.size());
					sm.setImage3(stateLocations.get(s3).getImage3());
					sm.setImage3ContentType(stateLocations.get(s3).getImage3ContentType());

					int s4 = (int) (Math.random() * stateLocations.size());
					sm.setImage4(stateLocations.get(s4).getImage4());
					sm.setImage4ContentType(stateLocations.get(s4).getImage4ContentType());

					int s5 = (int) (Math.random() * stateLocations.size());
					sm.setImage5(stateLocations.get(s5).getImage5());
					sm.setImage5ContentType(stateLocations.get(s5).getImage5ContentType());

				}

				sm.setLocationCount(locationCount);
				stateMediator.add(sm);
			}
		}

		return stateMediator;
	}

}
