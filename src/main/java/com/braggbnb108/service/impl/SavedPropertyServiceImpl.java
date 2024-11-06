package com.braggbnb108.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.service.impl.GenericServiceImpl;
import com.braggbnb108.dao.SavedPropertyDAO;
import com.braggbnb108.domain.SavedProperty;
import com.braggbnb108.dto.SavedPropertyDTO;
import com.braggbnb108.dto.SavedPropertySearchDTO;
import com.braggbnb108.dto.SavedPropertyPageDTO;
import com.braggbnb108.dto.SavedPropertyConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.SavedPropertyService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class SavedPropertyServiceImpl extends GenericServiceImpl<SavedProperty, Integer> implements SavedPropertyService {

    private final static Logger logger = LoggerFactory.getLogger(SavedPropertyServiceImpl.class);

	@Autowired
	SavedPropertyDAO savedPropertyDao;

	


	@Override
	public GenericDAO<SavedProperty, Integer> getDAO() {
		return (GenericDAO<SavedProperty, Integer>) savedPropertyDao;
	}
	
	public List<SavedProperty> findAll () {
		List<SavedProperty> savedPropertys = savedPropertyDao.findAll();
		
		return savedPropertys;	
		
	}

	public ResultDTO addSavedProperty(SavedPropertyDTO savedPropertyDTO, RequestDTO requestDTO) {

		SavedProperty savedProperty = new SavedProperty();

		savedProperty.setSavedPropertyId(savedPropertyDTO.getSavedPropertyId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		savedProperty = savedPropertyDao.save(savedProperty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SavedProperty> getAllSavedPropertys(Pageable pageable) {
		return savedPropertyDao.findAll(pageable);
	}

	public Page<SavedProperty> getAllSavedPropertys(Specification<SavedProperty> spec, Pageable pageable) {
		return savedPropertyDao.findAll(spec, pageable);
	}

	public ResponseEntity<SavedPropertyPageDTO> getSavedPropertys(SavedPropertySearchDTO savedPropertySearchDTO) {
	
			Integer savedPropertyId = savedPropertySearchDTO.getSavedPropertyId(); 
 			String sortBy = savedPropertySearchDTO.getSortBy();
			String sortOrder = savedPropertySearchDTO.getSortOrder();
			String searchQuery = savedPropertySearchDTO.getSearchQuery();
			Integer page = savedPropertySearchDTO.getPage();
			Integer size = savedPropertySearchDTO.getSize();

	        Specification<SavedProperty> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, savedPropertyId, "savedPropertyId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<SavedProperty> savedPropertys = this.getAllSavedPropertys(spec, pageable);
		
		//System.out.println(String.valueOf(savedPropertys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(savedPropertys.getTotalPages()));
		
		List<SavedProperty> savedPropertysList = savedPropertys.getContent();
		
		SavedPropertyConvertCriteriaDTO convertCriteria = new SavedPropertyConvertCriteriaDTO();
		List<SavedPropertyDTO> savedPropertyDTOs = this.convertSavedPropertysToSavedPropertyDTOs(savedPropertysList,convertCriteria);
		
		SavedPropertyPageDTO savedPropertyPageDTO = new SavedPropertyPageDTO();
		savedPropertyPageDTO.setSavedPropertys(savedPropertyDTOs);
		savedPropertyPageDTO.setTotalElements(savedPropertys.getTotalElements());
		return ResponseEntity.ok(savedPropertyPageDTO);
	}

	public List<SavedPropertyDTO> convertSavedPropertysToSavedPropertyDTOs(List<SavedProperty> savedPropertys, SavedPropertyConvertCriteriaDTO convertCriteria) {
		
		List<SavedPropertyDTO> savedPropertyDTOs = new ArrayList<SavedPropertyDTO>();
		
		for (SavedProperty savedProperty : savedPropertys) {
			savedPropertyDTOs.add(convertSavedPropertyToSavedPropertyDTO(savedProperty,convertCriteria));
		}
		
		return savedPropertyDTOs;

	}
	
	public SavedPropertyDTO convertSavedPropertyToSavedPropertyDTO(SavedProperty savedProperty, SavedPropertyConvertCriteriaDTO convertCriteria) {
		
		SavedPropertyDTO savedPropertyDTO = new SavedPropertyDTO();
		
		savedPropertyDTO.setSavedPropertyId(savedProperty.getSavedPropertyId());

	

		
		return savedPropertyDTO;
	}

	public ResultDTO updateSavedProperty(SavedPropertyDTO savedPropertyDTO, RequestDTO requestDTO) {
		
		SavedProperty savedProperty = savedPropertyDao.getById(savedPropertyDTO.getSavedPropertyId());

		savedProperty.setSavedPropertyId(ControllerUtils.setValue(savedProperty.getSavedPropertyId(), savedPropertyDTO.getSavedPropertyId()));



        savedProperty = savedPropertyDao.save(savedProperty);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SavedPropertyDTO getSavedPropertyDTOById(Integer savedPropertyId) {
	
		SavedProperty savedProperty = savedPropertyDao.getById(savedPropertyId);
			
		
		SavedPropertyConvertCriteriaDTO convertCriteria = new SavedPropertyConvertCriteriaDTO();
		return(this.convertSavedPropertyToSavedPropertyDTO(savedProperty,convertCriteria));
	}







}
