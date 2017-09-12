package com.butterfly.butterflyapp.service.mapper;

import com.butterfly.butterflyapp.domain.*;
import com.butterfly.butterflyapp.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Location and its DTO LocationDTO.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class, })
public interface LocationMapper extends EntityMapper <LocationDTO, Location> {

    @Mapping(source = "district.id", target = "districtId")
    LocationDTO toDto(Location location); 

    @Mapping(source = "districtId", target = "district")
    Location toEntity(LocationDTO locationDTO); 
    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
