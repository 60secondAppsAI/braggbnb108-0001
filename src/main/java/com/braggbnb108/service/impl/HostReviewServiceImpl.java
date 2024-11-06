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
import com.braggbnb108.dao.HostReviewDAO;
import com.braggbnb108.domain.HostReview;
import com.braggbnb108.dto.HostReviewDTO;
import com.braggbnb108.dto.HostReviewSearchDTO;
import com.braggbnb108.dto.HostReviewPageDTO;
import com.braggbnb108.dto.HostReviewConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.HostReviewService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class HostReviewServiceImpl extends GenericServiceImpl<HostReview, Integer> implements HostReviewService {

    private final static Logger logger = LoggerFactory.getLogger(HostReviewServiceImpl.class);

	@Autowired
	HostReviewDAO hostReviewDao;

	


	@Override
	public GenericDAO<HostReview, Integer> getDAO() {
		return (GenericDAO<HostReview, Integer>) hostReviewDao;
	}
	
	public List<HostReview> findAll () {
		List<HostReview> hostReviews = hostReviewDao.findAll();
		
		return hostReviews;	
		
	}

	public ResultDTO addHostReview(HostReviewDTO hostReviewDTO, RequestDTO requestDTO) {

		HostReview hostReview = new HostReview();

		hostReview.setHostReviewId(hostReviewDTO.getHostReviewId());


		hostReview.setRating(hostReviewDTO.getRating());


		hostReview.setComment(hostReviewDTO.getComment());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		hostReview = hostReviewDao.save(hostReview);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<HostReview> getAllHostReviews(Pageable pageable) {
		return hostReviewDao.findAll(pageable);
	}

	public Page<HostReview> getAllHostReviews(Specification<HostReview> spec, Pageable pageable) {
		return hostReviewDao.findAll(spec, pageable);
	}

	public ResponseEntity<HostReviewPageDTO> getHostReviews(HostReviewSearchDTO hostReviewSearchDTO) {
	
			Integer hostReviewId = hostReviewSearchDTO.getHostReviewId(); 
 			Integer rating = hostReviewSearchDTO.getRating(); 
 			String comment = hostReviewSearchDTO.getComment(); 
 			String sortBy = hostReviewSearchDTO.getSortBy();
			String sortOrder = hostReviewSearchDTO.getSortOrder();
			String searchQuery = hostReviewSearchDTO.getSearchQuery();
			Integer page = hostReviewSearchDTO.getPage();
			Integer size = hostReviewSearchDTO.getSize();

	        Specification<HostReview> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, hostReviewId, "hostReviewId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, rating, "rating"); 
			
			spec = ControllerUtils.andIfNecessary(spec, comment, "comment"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("comment")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<HostReview> hostReviews = this.getAllHostReviews(spec, pageable);
		
		//System.out.println(String.valueOf(hostReviews.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(hostReviews.getTotalPages()));
		
		List<HostReview> hostReviewsList = hostReviews.getContent();
		
		HostReviewConvertCriteriaDTO convertCriteria = new HostReviewConvertCriteriaDTO();
		List<HostReviewDTO> hostReviewDTOs = this.convertHostReviewsToHostReviewDTOs(hostReviewsList,convertCriteria);
		
		HostReviewPageDTO hostReviewPageDTO = new HostReviewPageDTO();
		hostReviewPageDTO.setHostReviews(hostReviewDTOs);
		hostReviewPageDTO.setTotalElements(hostReviews.getTotalElements());
		return ResponseEntity.ok(hostReviewPageDTO);
	}

	public List<HostReviewDTO> convertHostReviewsToHostReviewDTOs(List<HostReview> hostReviews, HostReviewConvertCriteriaDTO convertCriteria) {
		
		List<HostReviewDTO> hostReviewDTOs = new ArrayList<HostReviewDTO>();
		
		for (HostReview hostReview : hostReviews) {
			hostReviewDTOs.add(convertHostReviewToHostReviewDTO(hostReview,convertCriteria));
		}
		
		return hostReviewDTOs;

	}
	
	public HostReviewDTO convertHostReviewToHostReviewDTO(HostReview hostReview, HostReviewConvertCriteriaDTO convertCriteria) {
		
		HostReviewDTO hostReviewDTO = new HostReviewDTO();
		
		hostReviewDTO.setHostReviewId(hostReview.getHostReviewId());

	
		hostReviewDTO.setRating(hostReview.getRating());

	
		hostReviewDTO.setComment(hostReview.getComment());

	

		
		return hostReviewDTO;
	}

	public ResultDTO updateHostReview(HostReviewDTO hostReviewDTO, RequestDTO requestDTO) {
		
		HostReview hostReview = hostReviewDao.getById(hostReviewDTO.getHostReviewId());

		hostReview.setHostReviewId(ControllerUtils.setValue(hostReview.getHostReviewId(), hostReviewDTO.getHostReviewId()));

		hostReview.setRating(ControllerUtils.setValue(hostReview.getRating(), hostReviewDTO.getRating()));

		hostReview.setComment(ControllerUtils.setValue(hostReview.getComment(), hostReviewDTO.getComment()));



        hostReview = hostReviewDao.save(hostReview);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public HostReviewDTO getHostReviewDTOById(Integer hostReviewId) {
	
		HostReview hostReview = hostReviewDao.getById(hostReviewId);
			
		
		HostReviewConvertCriteriaDTO convertCriteria = new HostReviewConvertCriteriaDTO();
		return(this.convertHostReviewToHostReviewDTO(hostReview,convertCriteria));
	}







}
