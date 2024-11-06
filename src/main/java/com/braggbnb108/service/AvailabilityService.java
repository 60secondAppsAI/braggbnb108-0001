package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.Availability;
import com.braggbnb108.dto.AvailabilityDTO;
import com.braggbnb108.dto.AvailabilitySearchDTO;
import com.braggbnb108.dto.AvailabilityPageDTO;
import com.braggbnb108.dto.AvailabilityConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AvailabilityService extends GenericService<Availability, Integer> {

	List<Availability> findAll();

	ResultDTO addAvailability(AvailabilityDTO availabilityDTO, RequestDTO requestDTO);

	ResultDTO updateAvailability(AvailabilityDTO availabilityDTO, RequestDTO requestDTO);

    Page<Availability> getAllAvailabilitys(Pageable pageable);

    Page<Availability> getAllAvailabilitys(Specification<Availability> spec, Pageable pageable);

	ResponseEntity<AvailabilityPageDTO> getAvailabilitys(AvailabilitySearchDTO availabilitySearchDTO);
	
	List<AvailabilityDTO> convertAvailabilitysToAvailabilityDTOs(List<Availability> availabilitys, AvailabilityConvertCriteriaDTO convertCriteria);

	AvailabilityDTO getAvailabilityDTOById(Integer availabilityId);







}





