package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.Property;
import com.braggbnb108.dto.PropertyDTO;
import com.braggbnb108.dto.PropertySearchDTO;
import com.braggbnb108.dto.PropertyPageDTO;
import com.braggbnb108.dto.PropertyConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PropertyService extends GenericService<Property, Integer> {

	List<Property> findAll();

	ResultDTO addProperty(PropertyDTO propertyDTO, RequestDTO requestDTO);

	ResultDTO updateProperty(PropertyDTO propertyDTO, RequestDTO requestDTO);

    Page<Property> getAllPropertys(Pageable pageable);

    Page<Property> getAllPropertys(Specification<Property> spec, Pageable pageable);

	ResponseEntity<PropertyPageDTO> getPropertys(PropertySearchDTO propertySearchDTO);
	
	List<PropertyDTO> convertPropertysToPropertyDTOs(List<Property> propertys, PropertyConvertCriteriaDTO convertCriteria);

	PropertyDTO getPropertyDTOById(Integer propertyId);







}





