package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.SecurityDeposit;
import com.braggbnb108.dto.SecurityDepositDTO;
import com.braggbnb108.dto.SecurityDepositSearchDTO;
import com.braggbnb108.dto.SecurityDepositPageDTO;
import com.braggbnb108.dto.SecurityDepositConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SecurityDepositService extends GenericService<SecurityDeposit, Integer> {

	List<SecurityDeposit> findAll();

	ResultDTO addSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

	ResultDTO updateSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

    Page<SecurityDeposit> getAllSecurityDeposits(Pageable pageable);

    Page<SecurityDeposit> getAllSecurityDeposits(Specification<SecurityDeposit> spec, Pageable pageable);

	ResponseEntity<SecurityDepositPageDTO> getSecurityDeposits(SecurityDepositSearchDTO securityDepositSearchDTO);
	
	List<SecurityDepositDTO> convertSecurityDepositsToSecurityDepositDTOs(List<SecurityDeposit> securityDeposits, SecurityDepositConvertCriteriaDTO convertCriteria);

	SecurityDepositDTO getSecurityDepositDTOById(Integer securityDepositId);







}





