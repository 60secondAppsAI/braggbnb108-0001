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
import com.braggbnb108.dao.CleaningFeeDAO;
import com.braggbnb108.domain.CleaningFee;
import com.braggbnb108.dto.CleaningFeeDTO;
import com.braggbnb108.dto.CleaningFeeSearchDTO;
import com.braggbnb108.dto.CleaningFeePageDTO;
import com.braggbnb108.dto.CleaningFeeConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.CleaningFeeService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class CleaningFeeServiceImpl extends GenericServiceImpl<CleaningFee, Integer> implements CleaningFeeService {

    private final static Logger logger = LoggerFactory.getLogger(CleaningFeeServiceImpl.class);

	@Autowired
	CleaningFeeDAO cleaningFeeDao;

	


	@Override
	public GenericDAO<CleaningFee, Integer> getDAO() {
		return (GenericDAO<CleaningFee, Integer>) cleaningFeeDao;
	}
	
	public List<CleaningFee> findAll () {
		List<CleaningFee> cleaningFees = cleaningFeeDao.findAll();
		
		return cleaningFees;	
		
	}

	public ResultDTO addCleaningFee(CleaningFeeDTO cleaningFeeDTO, RequestDTO requestDTO) {

		CleaningFee cleaningFee = new CleaningFee();

		cleaningFee.setCleaningFeeId(cleaningFeeDTO.getCleaningFeeId());


		cleaningFee.setAmount(cleaningFeeDTO.getAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		cleaningFee = cleaningFeeDao.save(cleaningFee);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CleaningFee> getAllCleaningFees(Pageable pageable) {
		return cleaningFeeDao.findAll(pageable);
	}

	public Page<CleaningFee> getAllCleaningFees(Specification<CleaningFee> spec, Pageable pageable) {
		return cleaningFeeDao.findAll(spec, pageable);
	}

	public ResponseEntity<CleaningFeePageDTO> getCleaningFees(CleaningFeeSearchDTO cleaningFeeSearchDTO) {
	
			Integer cleaningFeeId = cleaningFeeSearchDTO.getCleaningFeeId(); 
  			String sortBy = cleaningFeeSearchDTO.getSortBy();
			String sortOrder = cleaningFeeSearchDTO.getSortOrder();
			String searchQuery = cleaningFeeSearchDTO.getSearchQuery();
			Integer page = cleaningFeeSearchDTO.getPage();
			Integer size = cleaningFeeSearchDTO.getSize();

	        Specification<CleaningFee> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cleaningFeeId, "cleaningFeeId"); 
			
			

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

		Page<CleaningFee> cleaningFees = this.getAllCleaningFees(spec, pageable);
		
		//System.out.println(String.valueOf(cleaningFees.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(cleaningFees.getTotalPages()));
		
		List<CleaningFee> cleaningFeesList = cleaningFees.getContent();
		
		CleaningFeeConvertCriteriaDTO convertCriteria = new CleaningFeeConvertCriteriaDTO();
		List<CleaningFeeDTO> cleaningFeeDTOs = this.convertCleaningFeesToCleaningFeeDTOs(cleaningFeesList,convertCriteria);
		
		CleaningFeePageDTO cleaningFeePageDTO = new CleaningFeePageDTO();
		cleaningFeePageDTO.setCleaningFees(cleaningFeeDTOs);
		cleaningFeePageDTO.setTotalElements(cleaningFees.getTotalElements());
		return ResponseEntity.ok(cleaningFeePageDTO);
	}

	public List<CleaningFeeDTO> convertCleaningFeesToCleaningFeeDTOs(List<CleaningFee> cleaningFees, CleaningFeeConvertCriteriaDTO convertCriteria) {
		
		List<CleaningFeeDTO> cleaningFeeDTOs = new ArrayList<CleaningFeeDTO>();
		
		for (CleaningFee cleaningFee : cleaningFees) {
			cleaningFeeDTOs.add(convertCleaningFeeToCleaningFeeDTO(cleaningFee,convertCriteria));
		}
		
		return cleaningFeeDTOs;

	}
	
	public CleaningFeeDTO convertCleaningFeeToCleaningFeeDTO(CleaningFee cleaningFee, CleaningFeeConvertCriteriaDTO convertCriteria) {
		
		CleaningFeeDTO cleaningFeeDTO = new CleaningFeeDTO();
		
		cleaningFeeDTO.setCleaningFeeId(cleaningFee.getCleaningFeeId());

	
		cleaningFeeDTO.setAmount(cleaningFee.getAmount());

	

		
		return cleaningFeeDTO;
	}

	public ResultDTO updateCleaningFee(CleaningFeeDTO cleaningFeeDTO, RequestDTO requestDTO) {
		
		CleaningFee cleaningFee = cleaningFeeDao.getById(cleaningFeeDTO.getCleaningFeeId());

		cleaningFee.setCleaningFeeId(ControllerUtils.setValue(cleaningFee.getCleaningFeeId(), cleaningFeeDTO.getCleaningFeeId()));

		cleaningFee.setAmount(ControllerUtils.setValue(cleaningFee.getAmount(), cleaningFeeDTO.getAmount()));



        cleaningFee = cleaningFeeDao.save(cleaningFee);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CleaningFeeDTO getCleaningFeeDTOById(Integer cleaningFeeId) {
	
		CleaningFee cleaningFee = cleaningFeeDao.getById(cleaningFeeId);
			
		
		CleaningFeeConvertCriteriaDTO convertCriteria = new CleaningFeeConvertCriteriaDTO();
		return(this.convertCleaningFeeToCleaningFeeDTO(cleaningFee,convertCriteria));
	}







}
