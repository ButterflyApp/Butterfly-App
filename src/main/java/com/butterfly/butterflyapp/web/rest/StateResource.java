package com.butterfly.butterflyapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.domain.Location;
import com.butterfly.butterflyapp.domain.State;
import com.butterfly.butterflyapp.domain.StateMediator;
import com.butterfly.butterflyapp.service.DistrictService;
import com.butterfly.butterflyapp.service.LocationService;
import com.butterfly.butterflyapp.service.StateService;
import com.butterfly.butterflyapp.web.rest.util.HeaderUtil;
import com.butterfly.butterflyapp.service.dto.StateDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing State.
 */
@RestController
@RequestMapping("/api")
public class StateResource {

	private final Logger log = LoggerFactory.getLogger(StateResource.class);

	private static final String ENTITY_NAME = "state";

	private final StateService stateService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private LocationService locationService;

	public StateResource(StateService stateService) {
		this.stateService = stateService;
	}

	/**
	 * POST /states : Create a new state.
	 *
	 * @param stateDTO
	 *            the stateDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         stateDTO, or with status 400 (Bad Request) if the state has already
	 *         an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/states")
	@Timed
	public ResponseEntity<StateDTO> createState(@RequestBody StateDTO stateDTO) throws URISyntaxException {
		log.debug("REST request to save State : {}", stateDTO);
		if (stateDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new state cannot already have an ID"))
					.body(null);
		}
		StateDTO result = stateService.save(stateDTO);
		return ResponseEntity.created(new URI("/api/states/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /states : Updates an existing state.
	 *
	 * @param stateDTO
	 *            the stateDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         stateDTO, or with status 400 (Bad Request) if the stateDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the stateDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/states")
	@Timed
	public ResponseEntity<StateDTO> updateState(@RequestBody StateDTO stateDTO) throws URISyntaxException {
		log.debug("REST request to update State : {}", stateDTO);
		if (stateDTO.getId() == null) {
			return createState(stateDTO);
		}
		StateDTO result = stateService.save(stateDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stateDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /states : get all the states.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of states in
	 *         body
	 */
	@GetMapping("/states")
	@Timed
	public List<StateDTO> getAllStates() {
		log.debug("REST request to get all States");
		return stateService.findAll();
	}

	/**
	 * GET /states/:id : get the "id" state.
	 *
	 * @param id
	 *            the id of the stateDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the stateDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/states/{id}")
	@Timed
	public ResponseEntity<StateDTO> getState(@PathVariable Long id) {
		log.debug("REST request to get State : {}", id);
		StateDTO stateDTO = stateService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stateDTO));
	}

	/**
	 * DELETE /states/:id : delete the "id" state.
	 *
	 * @param id
	 *            the id of the stateDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/states/{id}")
	@Timed
	public ResponseEntity<Void> deleteState(@PathVariable Long id) {
		log.debug("REST request to delete State : {}", id);
		stateService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@GetMapping("/states/getAll")
	public List<StateDTO> findAllStates() {

		return stateService.findAll();
	}

	@GetMapping("/states/allData")
	public List<StateMediator> getAllData() {

		List<StateMediator> list = new ArrayList<StateMediator>();
		List<StateDTO> stateList = stateService.findAll();
		List<District> dList = null;
		List<Location> locationList = null;

		long locationCount = 0;

		for (int i = 0; i < stateList.size(); i++) {
			StateDTO st = stateList.get(i);
			dList = null;
			locationCount = 0;
			List<Location> stateLocations = new ArrayList<Location>();
			dList = districtService.findByStateId(st.getId());
			StateMediator stateMediator = new StateMediator();

			for (int k = 0; k < dList.size(); k++) {

				locationList = locationService.findByDistrictId(dList.get(k).getId());
				for (Location loc : locationList) {
					stateLocations.add(loc);
				}

				 //locationCount = (locationCount + locationList.size());
				locationCount = stateLocations.size();

			}

			stateMediator.setId(st.getId());
			stateMediator.setName(st.getStateName());
			stateMediator.setDistrictCount(dList.size());
			stateMediator.setLocationCount(locationCount);

			if (stateLocations.size() >= 5) {

				int s = (int) (Math.random() * stateLocations.size() );
				stateMediator.setImage1(stateLocations.get(s).getImage1());
				stateMediator.setImage1ContentType(stateLocations.get(s).getImage1ContentType());

				int s2 = (int) (Math.random() * stateLocations.size());
				stateMediator.setImage2(stateLocations.get(s2).getImage2());
				stateMediator.setImage2ContentType(stateLocations.get(s2).getImage2ContentType());

				int s3 = (int)( Math.random() * stateLocations.size());
				stateMediator.setImage3(stateLocations.get(s3).getImage3());
				stateMediator.setImage3ContentType(stateLocations.get(s3).getImage3ContentType());

				int s4 = (int) (Math.random() * stateLocations.size());
				stateMediator.setImage4(stateLocations.get(s4).getImage4());
				stateMediator.setImage4ContentType(stateLocations.get(s4).getImage4ContentType());

				int s5 = (int) (Math.random() * stateLocations.size());
				stateMediator.setImage5(stateLocations.get(s5).getImage5());
				stateMediator.setImage5ContentType(stateLocations.get(s5).getImage5ContentType());
				
				
			}
			list.add(stateMediator);

		}

		return list;

	}


}
