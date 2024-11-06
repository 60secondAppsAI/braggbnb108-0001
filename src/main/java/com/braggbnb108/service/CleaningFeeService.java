package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.CleaningFee;
import com.braggbnb108.dto.CleaningFeeDTO;
import com.braggbnb108.dto.CleaningFeeSearchDTO;
import com.braggbnb108.dto.CleaningFeePageDTO;
import com.braggbnb108.dto.CleaningFeeConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CleaningFeeService extends GenericService<CleaningFee, Integer> {

	List<CleaningFee> findAll();

	ResultDTO addCleaningFee(CleaningFeeDTO cleaningFeeDTO, RequestDTO requestDTO);

	ResultDTO updateCleaningFee(CleaningFeeDTO cleaningFeeDTO, RequestDTO requestDTO);

    Page<CleaningFee> getAllCleaningFees(Pageable pageable);

    Page<CleaningFee> getAllCleaningFees(Specification<CleaningFee> spec, Pageable pageable);

	ResponseEntity<CleaningFeePageDTO> getCleaningFees(CleaningFeeSearchDTO cleaningFeeSearchDTO);
	
	List<CleaningFeeDTO> convertCleaningFeesToCleaningFeeDTOs(List<CleaningFee> cleaningFees, CleaningFeeConvertCriteriaDTO convertCriteria);

	CleaningFeeDTO getCleaningFeeDTOById(Integer cleaningFeeId);







}





