package com.butterfly.butterflyapp.repository;

import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.domain.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
	
	public List<Location> findByDistrictId(Long id);
	public Location findById(Long id);
    
}
