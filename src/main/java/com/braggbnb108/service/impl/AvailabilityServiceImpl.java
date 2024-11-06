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
import com.braggbnb108.dao.AvailabilityDAO;
import com.braggbnb108.domain.Availability;
import com.braggbnb108.dto.AvailabilityDTO;
import com.braggbnb108.dto.AvailabilitySearchDTO;
import com.braggbnb108.dto.AvailabilityPageDTO;
import com.braggbnb108.dto.AvailabilityConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.AvailabilityService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class AvailabilityServiceImpl extends GenericServiceImpl<Availability, Integer> implements AvailabilityService {

    private final static Logger logger = LoggerFactory.getLogger(AvailabilityServiceImpl.class);

	@Autowired
	AvailabilityDAO availabilityDao;

	


	@Override
	public GenericDAO<Availability, Integer> getDAO() {
		return (GenericDAO<Availability, Integer>) availabilityDao;
	}
	
	public List<Availability> findAll () {
		List<Availability> availabilitys = availabilityDao.findAll();
		
		return availabilitys;	
		
	}

	public ResultDTO addAvailability(AvailabilityDTO availabilityDTO, RequestDTO requestDTO) {

		Availability availability = new Availability();

		availability.setAvailabilityId(availabilityDTO.getAvailabilityId());


		availability.setDate(availabilityDTO.getDate());


		availability.setIsAvailable(availabilityDTO.getIsAvailable());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		availability = availabilityDao.save(availability);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Availability> getAllAvailabilitys(Pageable pageable) {
		return availabilityDao.findAll(pageable);
	}

	public Page<Availability> getAllAvailabilitys(Specification<Availability> spec, Pageable pageable) {
		return availabilityDao.findAll(spec, pageable);
	}

	public ResponseEntity<AvailabilityPageDTO> getAvailabilitys(AvailabilitySearchDTO availabilitySearchDTO) {
	
			Integer availabilityId = availabilitySearchDTO.getAvailabilityId(); 
    			String sortBy = availabilitySearchDTO.getSortBy();
			String sortOrder = availabilitySearchDTO.getSortOrder();
			String searchQuery = availabilitySearchDTO.getSearchQuery();
			Integer page = availabilitySearchDTO.getPage();
			Integer size = availabilitySearchDTO.getSize();

	        Specification<Availability> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, availabilityId, "availabilityId"); 
			
 			
			

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

		Page<Availability> availabilitys = this.getAllAvailabilitys(spec, pageable);
		
		//System.out.println(String.valueOf(availabilitys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(availabilitys.getTotalPages()));
		
		List<Availability> availabilitysList = availabilitys.getContent();
		
		AvailabilityConvertCriteriaDTO convertCriteria = new AvailabilityConvertCriteriaDTO();
		List<AvailabilityDTO> availabilityDTOs = this.convertAvailabilitysToAvailabilityDTOs(availabilitysList,convertCriteria);
		
		AvailabilityPageDTO availabilityPageDTO = new AvailabilityPageDTO();
		availabilityPageDTO.setAvailabilitys(availabilityDTOs);
		availabilityPageDTO.setTotalElements(availabilitys.getTotalElements());
		return ResponseEntity.ok(availabilityPageDTO);
	}

	public List<AvailabilityDTO> convertAvailabilitysToAvailabilityDTOs(List<Availability> availabilitys, AvailabilityConvertCriteriaDTO convertCriteria) {
		
		List<AvailabilityDTO> availabilityDTOs = new ArrayList<AvailabilityDTO>();
		
		for (Availability availability : availabilitys) {
			availabilityDTOs.add(convertAvailabilityToAvailabilityDTO(availability,convertCriteria));
		}
		
		return availabilityDTOs;

	}
	
	public AvailabilityDTO convertAvailabilityToAvailabilityDTO(Availability availability, AvailabilityConvertCriteriaDTO convertCriteria) {
		
		AvailabilityDTO availabilityDTO = new AvailabilityDTO();
		
		availabilityDTO.setAvailabilityId(availability.getAvailabilityId());

	
		availabilityDTO.setDate(availability.getDate());

	
		availabilityDTO.setIsAvailable(availability.getIsAvailable());

	

		
		return availabilityDTO;
	}

	public ResultDTO updateAvailability(AvailabilityDTO availabilityDTO, RequestDTO requestDTO) {
		
		Availability availability = availabilityDao.getById(availabilityDTO.getAvailabilityId());

		availability.setAvailabilityId(ControllerUtils.setValue(availability.getAvailabilityId(), availabilityDTO.getAvailabilityId()));

		availability.setDate(ControllerUtils.setValue(availability.getDate(), availabilityDTO.getDate()));

		availability.setIsAvailable(ControllerUtils.setValue(availability.getIsAvailable(), availabilityDTO.getIsAvailable()));



        availability = availabilityDao.save(availability);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AvailabilityDTO getAvailabilityDTOById(Integer availabilityId) {
	
		Availability availability = availabilityDao.getById(availabilityId);
			
		
		AvailabilityConvertCriteriaDTO convertCriteria = new AvailabilityConvertCriteriaDTO();
		return(this.convertAvailabilityToAvailabilityDTO(availability,convertCriteria));
	}







}
