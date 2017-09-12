package com.butterfly.butterflyapp.service.mapper;

import com.butterfly.butterflyapp.domain.*;
import com.butterfly.butterflyapp.service.dto.CountryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Country and its DTO CountryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CountryMapper extends EntityMapper <CountryDTO, Country> {
    
    @Mapping(target = "states", ignore = true)
    Country toEntity(CountryDTO countryDTO); 
    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
