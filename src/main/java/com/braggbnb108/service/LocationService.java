package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.Location;
import com.braggbnb108.dto.LocationDTO;
import com.braggbnb108.dto.LocationSearchDTO;
import com.braggbnb108.dto.LocationPageDTO;
import com.braggbnb108.dto.LocationConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LocationService extends GenericService<Location, Integer> {

	List<Location> findAll();

	ResultDTO addLocation(LocationDTO locationDTO, RequestDTO requestDTO);

	ResultDTO updateLocation(LocationDTO locationDTO, RequestDTO requestDTO);

    Page<Location> getAllLocations(Pageable pageable);

    Page<Location> getAllLocations(Specification<Location> spec, Pageable pageable);

	ResponseEntity<LocationPageDTO> getLocations(LocationSearchDTO locationSearchDTO);
	
	List<LocationDTO> convertLocationsToLocationDTOs(List<Location> locations, LocationConvertCriteriaDTO convertCriteria);

	LocationDTO getLocationDTOById(Integer locationId);







}





