package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.HostReview;
import com.braggbnb108.dto.HostReviewDTO;
import com.braggbnb108.dto.HostReviewSearchDTO;
import com.braggbnb108.dto.HostReviewPageDTO;
import com.braggbnb108.dto.HostReviewConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostReviewService extends GenericService<HostReview, Integer> {

	List<HostReview> findAll();

	ResultDTO addHostReview(HostReviewDTO hostReviewDTO, RequestDTO requestDTO);

	ResultDTO updateHostReview(HostReviewDTO hostReviewDTO, RequestDTO requestDTO);

    Page<HostReview> getAllHostReviews(Pageable pageable);

    Page<HostReview> getAllHostReviews(Specification<HostReview> spec, Pageable pageable);

	ResponseEntity<HostReviewPageDTO> getHostReviews(HostReviewSearchDTO hostReviewSearchDTO);
	
	List<HostReviewDTO> convertHostReviewsToHostReviewDTOs(List<HostReview> hostReviews, HostReviewConvertCriteriaDTO convertCriteria);

	HostReviewDTO getHostReviewDTOById(Integer hostReviewId);







}





