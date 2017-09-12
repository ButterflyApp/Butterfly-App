package com.butterfly.butterflyapp.service.impl;

import com.butterfly.butterflyapp.service.DistrictService;
import com.butterfly.butterflyapp.domain.District;
import com.butterfly.butterflyapp.repository.DistrictRepository;
import com.butterfly.butterflyapp.service.dto.DistrictDTO;
import com.butterfly.butterflyapp.service.mapper.DistrictMapper;

import net.bytebuddy.asm.Advice.Return;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing District.
 */
@Service
@Transactional
public class DistrictServiceImpl implements DistrictService{

    private final Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);

    private final DistrictRepository districtRepository;

    private final DistrictMapper districtMapper;
    private DistrictService districtService;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    /**
     * Save a district.
     *
     * @param districtDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        log.debug("Request to save District : {}", districtDTO);
        District district = districtMapper.toEntity(districtDTO);
        district = districtRepository.save(district);
        return districtMapper.toDto(district);
    }

    /**
     *  Get all the districts.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DistrictDTO> findAll() {
        log.debug("Request to get all Districts");
        return districtRepository.findAll().stream()
            .map(districtMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one district by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DistrictDTO findOne(Long id) {
        log.debug("Request to get District : {}", id);
        District district = districtRepository.findOne(id);
        return districtMapper.toDto(district);
    }

    /**
     *  Delete the  district by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete District : {}", id);
        districtRepository.delete(id);
    }

	@Override
	public List<District> findByStateId(Long id) {
		
		return districtRepository.findByStateId(id);
	}

	
}
