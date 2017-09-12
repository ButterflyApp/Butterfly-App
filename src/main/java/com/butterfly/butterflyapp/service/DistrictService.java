package com.butterfly.butterflyapp.service;

import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.service.dto.DistrictDTO;
import java.util.List;

/**
 * Service Interface for managing District.
 */
public interface DistrictService {

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    DistrictDTO save(DistrictDTO districtDTO);

    /**
     *  Get all the districts.
     *
     *  @return the list of entities
     */
    List<DistrictDTO> findAll();

    /**
     *  Get the "id" district.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DistrictDTO findOne(Long id);

    /**
     *  Delete the "id" district.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    public List<District> findByStateId(Long id);
  
}
