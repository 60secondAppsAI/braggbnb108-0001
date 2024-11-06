package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.SavedProperty;
import com.braggbnb108.dto.SavedPropertyDTO;
import com.braggbnb108.dto.SavedPropertySearchDTO;
import com.braggbnb108.dto.SavedPropertyPageDTO;
import com.braggbnb108.dto.SavedPropertyConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SavedPropertyService extends GenericService<SavedProperty, Integer> {

	List<SavedProperty> findAll();

	ResultDTO addSavedProperty(SavedPropertyDTO savedPropertyDTO, RequestDTO requestDTO);

	ResultDTO updateSavedProperty(SavedPropertyDTO savedPropertyDTO, RequestDTO requestDTO);

    Page<SavedProperty> getAllSavedPropertys(Pageable pageable);

    Page<SavedProperty> getAllSavedPropertys(Specification<SavedProperty> spec, Pageable pageable);

	ResponseEntity<SavedPropertyPageDTO> getSavedPropertys(SavedPropertySearchDTO savedPropertySearchDTO);
	
	List<SavedPropertyDTO> convertSavedPropertysToSavedPropertyDTOs(List<SavedProperty> savedPropertys, SavedPropertyConvertCriteriaDTO convertCriteria);

	SavedPropertyDTO getSavedPropertyDTOById(Integer savedPropertyId);







}





