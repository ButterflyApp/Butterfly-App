package com.butterfly.butterflyapp.repository;

import com.butterfly.butterflyapp.domain.District;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the District entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {
	
	public List<District> findByStateId(Long id);
	
	
	

    
}
