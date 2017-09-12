package com.butterfly.butterflyapp.service.mapper;

import com.butterfly.butterflyapp.domain.*;
import com.butterfly.butterflyapp.service.dto.DistrictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity District and its DTO DistrictDTO.
 */
@Mapper(componentModel = "spring", uses = {StateMapper.class, })
public interface DistrictMapper extends EntityMapper <DistrictDTO, District> {

    @Mapping(source = "state.id", target = "stateId")
    DistrictDTO toDto(District district); 

    @Mapping(source = "stateId", target = "state")
    @Mapping(target = "locations", ignore = true)
    District toEntity(DistrictDTO districtDTO); 
    default District fromId(Long id) {
        if (id == null) {
            return null;
        }
        District district = new District();
        district.setId(id);
        return district;
    }
}
