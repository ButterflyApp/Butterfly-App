package com.butterfly.butterflyapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.domain.DistrictMediator;
import com.butterfly.butterflyapp.domain.Location;
import com.butterfly.butterflyapp.service.DistrictService;
import com.butterfly.butterflyapp.service.LocationService;
import com.butterfly.butterflyapp.web.rest.util.HeaderUtil;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing District.
 */
@RestController
@RequestMapping("/api")
public class DistrictResource {

	private final Logger log = LoggerFactory.getLogger(DistrictResource.class);

	private static final String ENTITY_NAME = "district";

	@Autowired
	private LocationService locationService;

	private final DistrictService districtService;

	public DistrictResource(DistrictService districtService) {
		this.districtService = districtService;
	}

	/**
	 * POST /districts : Create a new district.
	 *
	 * @param districtDTO
	 *            the districtDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         districtDTO, or with status 400 (Bad Request) if the district has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/districts")
	@Timed
	public ResponseEntity<DistrictDTO> createDistrict(@RequestBody DistrictDTO districtDTO) throws URISyntaxException {
		log.debug("REST request to save District : {}", districtDTO);
		if (districtDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new district cannot already have an ID"))
					.body(null);
		}
		DistrictDTO result = districtService.save(districtDTO);
		return ResponseEntity.created(new URI("/api/districts/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /districts : Updates an existing district.
	 *
	 * @param districtDTO
	 *            the districtDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         districtDTO, or with status 400 (Bad Request) if the districtDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         districtDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/districts")
	@Timed
	public ResponseEntity<DistrictDTO> updateDistrict(@RequestBody DistrictDTO districtDTO) throws URISyntaxException {
		log.debug("REST request to update District : {}", districtDTO);
		if (districtDTO.getId() == null) {
			return createDistrict(districtDTO);
		}
		DistrictDTO result = districtService.save(districtDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, districtDTO.getId().toString())).body(result);
	}

	/**
	 * GET /districts : get all the districts.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of districts in
	 *         body
	 */
	@GetMapping("/districts")
	@Timed
	public List<DistrictDTO> getAllDistricts() {
		log.debug("REST request to get all Districts");
		return districtService.findAll();
	}

	/**
	 * GET /districts/:id : get the "id" district.
	 *
	 * @param id
	 *            the id of the districtDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         districtDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/districts/{id}")
	@Timed
	public ResponseEntity<DistrictDTO> getDistrict(@PathVariable Long id) {
		log.debug("REST request to get District : {}", id);
		DistrictDTO districtDTO = districtService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(districtDTO));
	}

	/**
	 * DELETE /districts/:id : delete the "id" district.
	 *
	 * @param id
	 *            the id of the districtDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/districts/{id}")
	@Timed
	public ResponseEntity<Void> deleteDistrict(@PathVariable Long id) {
		log.debug("REST request to delete District : {}", id);
		districtService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@GetMapping("/districts/stateId/{id}")
	public Integer findDistrictsByStateId(@PathVariable Long id) {

		List<District> list = districtService.findByStateId(id);
		return list.size();

	}

	@GetMapping("/districts/state/{id}")
	public List<District> findDistrictsByAllState(@PathVariable Long id) {

		return districtService.findByStateId(id);

	}

	@GetMapping("/location/districts/state/{id}")
	public List<DistrictMediator> findDistrictsAndLocationCnt(@PathVariable Long id) {

		List<District> distList = districtService.findByStateId(id);
		List<DistrictMediator> districtMediator = new ArrayList<DistrictMediator>();

		for (District distr : distList) {
			DistrictMediator d = new DistrictMediator();
			d.setStateName(distr.getState().getStateName());
			d.setId(distr.getId());
			d.setDistrictName(distr.getDistrictName());
			List<Location> locations = locationService.findByDistrictId(distr.getId());
			d.setLocationCount(locations.size());
			districtMediator.add(d);

		}

		return districtMediator;

	}
	
	@GetMapping("/districts/state/all/{id}")
	public List<DistrictMediator> findDistrictLocations(@PathVariable Long id) {

		List<District> distList = districtService.findByStateId(id);
		List<DistrictMediator> districtMediator = new ArrayList<DistrictMediator>();
		
		for (District distr : distList) {
			List<Location>locationList=new ArrayList<Location>();
			DistrictMediator d = new DistrictMediator();
			d.setStateName(distr.getState().getStateName());
			d.setId(distr.getId());
			d.setDistrictName(distr.getDistrictName());
			
			Set<Location> locations=distr.getLocations();
			for(Location location:locations) {
				
				locationList.add(location);
			}
			
			if (locationList.size() != 0) {

				int s = (int) (Math.random() * locationList.size());
				d.setImage1(locationList.get(s).getImage1());
				d.setImage1ContentType(locationList.get(s).getImage1ContentType());

				int s2 = (int) (Math.random() * locationList.size());
				d.setImage2(locationList.get(s2).getImage2());
				d.setImage2ContentType(locationList.get(s2).getImage2ContentType());

				int s3 = (int) (Math.random() * locationList.size());
				d.setImage3(locationList.get(s3).getImage3());
				d.setImage3ContentType(locationList.get(s3).getImage3ContentType());

				int s4 = (int) (Math.random() * locationList.size());
				d.setImage4(locationList.get(s4).getImage4());
				d.setImage4ContentType(locationList.get(s4).getImage4ContentType());

				int s5 = (int) (Math.random() * locationList.size());
				d.setImage5(locationList.get(s5).getImage5());
				d.setImage5ContentType(locationList.get(s5).getImage5ContentType());

			}
			
			
			
		//	List<Location> locations = locationService.findByDistrictId(distr.getId());
			d.setLocationCount(locations.size());
			districtMediator.add(d);

		}

		return districtMediator;

	}


}
