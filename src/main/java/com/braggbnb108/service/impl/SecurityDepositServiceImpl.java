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
import com.braggbnb108.dao.SecurityDepositDAO;
import com.braggbnb108.domain.SecurityDeposit;
import com.braggbnb108.dto.SecurityDepositDTO;
import com.braggbnb108.dto.SecurityDepositSearchDTO;
import com.braggbnb108.dto.SecurityDepositPageDTO;
import com.braggbnb108.dto.SecurityDepositConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.SecurityDepositService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class SecurityDepositServiceImpl extends GenericServiceImpl<SecurityDeposit, Integer> implements SecurityDepositService {

    private final static Logger logger = LoggerFactory.getLogger(SecurityDepositServiceImpl.class);

	@Autowired
	SecurityDepositDAO securityDepositDao;

	


	@Override
	public GenericDAO<SecurityDeposit, Integer> getDAO() {
		return (GenericDAO<SecurityDeposit, Integer>) securityDepositDao;
	}
	
	public List<SecurityDeposit> findAll () {
		List<SecurityDeposit> securityDeposits = securityDepositDao.findAll();
		
		return securityDeposits;	
		
	}

	public ResultDTO addSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO) {

		SecurityDeposit securityDeposit = new SecurityDeposit();

		securityDeposit.setSecurityDepositId(securityDepositDTO.getSecurityDepositId());


		securityDeposit.setAmount(securityDepositDTO.getAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		securityDeposit = securityDepositDao.save(securityDeposit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SecurityDeposit> getAllSecurityDeposits(Pageable pageable) {
		return securityDepositDao.findAll(pageable);
	}

	public Page<SecurityDeposit> getAllSecurityDeposits(Specification<SecurityDeposit> spec, Pageable pageable) {
		return securityDepositDao.findAll(spec, pageable);
	}

	public ResponseEntity<SecurityDepositPageDTO> getSecurityDeposits(SecurityDepositSearchDTO securityDepositSearchDTO) {
	
			Integer securityDepositId = securityDepositSearchDTO.getSecurityDepositId(); 
  			String sortBy = securityDepositSearchDTO.getSortBy();
			String sortOrder = securityDepositSearchDTO.getSortOrder();
			String searchQuery = securityDepositSearchDTO.getSearchQuery();
			Integer page = securityDepositSearchDTO.getPage();
			Integer size = securityDepositSearchDTO.getSize();

	        Specification<SecurityDeposit> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, securityDepositId, "securityDepositId"); 
			
			

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

		Page<SecurityDeposit> securityDeposits = this.getAllSecurityDeposits(spec, pageable);
		
		//System.out.println(String.valueOf(securityDeposits.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(securityDeposits.getTotalPages()));
		
		List<SecurityDeposit> securityDepositsList = securityDeposits.getContent();
		
		SecurityDepositConvertCriteriaDTO convertCriteria = new SecurityDepositConvertCriteriaDTO();
		List<SecurityDepositDTO> securityDepositDTOs = this.convertSecurityDepositsToSecurityDepositDTOs(securityDepositsList,convertCriteria);
		
		SecurityDepositPageDTO securityDepositPageDTO = new SecurityDepositPageDTO();
		securityDepositPageDTO.setSecurityDeposits(securityDepositDTOs);
		securityDepositPageDTO.setTotalElements(securityDeposits.getTotalElements());
		return ResponseEntity.ok(securityDepositPageDTO);
	}

	public List<SecurityDepositDTO> convertSecurityDepositsToSecurityDepositDTOs(List<SecurityDeposit> securityDeposits, SecurityDepositConvertCriteriaDTO convertCriteria) {
		
		List<SecurityDepositDTO> securityDepositDTOs = new ArrayList<SecurityDepositDTO>();
		
		for (SecurityDeposit securityDeposit : securityDeposits) {
			securityDepositDTOs.add(convertSecurityDepositToSecurityDepositDTO(securityDeposit,convertCriteria));
		}
		
		return securityDepositDTOs;

	}
	
	public SecurityDepositDTO convertSecurityDepositToSecurityDepositDTO(SecurityDeposit securityDeposit, SecurityDepositConvertCriteriaDTO convertCriteria) {
		
		SecurityDepositDTO securityDepositDTO = new SecurityDepositDTO();
		
		securityDepositDTO.setSecurityDepositId(securityDeposit.getSecurityDepositId());

	
		securityDepositDTO.setAmount(securityDeposit.getAmount());

	

		
		return securityDepositDTO;
	}

	public ResultDTO updateSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO) {
		
		SecurityDeposit securityDeposit = securityDepositDao.getById(securityDepositDTO.getSecurityDepositId());

		securityDeposit.setSecurityDepositId(ControllerUtils.setValue(securityDeposit.getSecurityDepositId(), securityDepositDTO.getSecurityDepositId()));

		securityDeposit.setAmount(ControllerUtils.setValue(securityDeposit.getAmount(), securityDepositDTO.getAmount()));



        securityDeposit = securityDepositDao.save(securityDeposit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SecurityDepositDTO getSecurityDepositDTOById(Integer securityDepositId) {
	
		SecurityDeposit securityDeposit = securityDepositDao.getById(securityDepositId);
			
		
		SecurityDepositConvertCriteriaDTO convertCriteria = new SecurityDepositConvertCriteriaDTO();
		return(this.convertSecurityDepositToSecurityDepositDTO(securityDeposit,convertCriteria));
	}







}
