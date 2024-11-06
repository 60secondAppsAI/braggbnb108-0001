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
import com.braggbnb108.dao.BookingRequestDAO;
import com.braggbnb108.domain.BookingRequest;
import com.braggbnb108.dto.BookingRequestDTO;
import com.braggbnb108.dto.BookingRequestSearchDTO;
import com.braggbnb108.dto.BookingRequestPageDTO;
import com.braggbnb108.dto.BookingRequestConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.BookingRequestService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class BookingRequestServiceImpl extends GenericServiceImpl<BookingRequest, Integer> implements BookingRequestService {

    private final static Logger logger = LoggerFactory.getLogger(BookingRequestServiceImpl.class);

	@Autowired
	BookingRequestDAO bookingRequestDao;

	


	@Override
	public GenericDAO<BookingRequest, Integer> getDAO() {
		return (GenericDAO<BookingRequest, Integer>) bookingRequestDao;
	}
	
	public List<BookingRequest> findAll () {
		List<BookingRequest> bookingRequests = bookingRequestDao.findAll();
		
		return bookingRequests;	
		
	}

	public ResultDTO addBookingRequest(BookingRequestDTO bookingRequestDTO, RequestDTO requestDTO) {

		BookingRequest bookingRequest = new BookingRequest();

		bookingRequest.setBookingRequestId(bookingRequestDTO.getBookingRequestId());


		bookingRequest.setRequestDate(bookingRequestDTO.getRequestDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		bookingRequest = bookingRequestDao.save(bookingRequest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BookingRequest> getAllBookingRequests(Pageable pageable) {
		return bookingRequestDao.findAll(pageable);
	}

	public Page<BookingRequest> getAllBookingRequests(Specification<BookingRequest> spec, Pageable pageable) {
		return bookingRequestDao.findAll(spec, pageable);
	}

	public ResponseEntity<BookingRequestPageDTO> getBookingRequests(BookingRequestSearchDTO bookingRequestSearchDTO) {
	
			Integer bookingRequestId = bookingRequestSearchDTO.getBookingRequestId(); 
   			String sortBy = bookingRequestSearchDTO.getSortBy();
			String sortOrder = bookingRequestSearchDTO.getSortOrder();
			String searchQuery = bookingRequestSearchDTO.getSearchQuery();
			Integer page = bookingRequestSearchDTO.getPage();
			Integer size = bookingRequestSearchDTO.getSize();

	        Specification<BookingRequest> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, bookingRequestId, "bookingRequestId"); 
			
 			

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

		Page<BookingRequest> bookingRequests = this.getAllBookingRequests(spec, pageable);
		
		//System.out.println(String.valueOf(bookingRequests.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(bookingRequests.getTotalPages()));
		
		List<BookingRequest> bookingRequestsList = bookingRequests.getContent();
		
		BookingRequestConvertCriteriaDTO convertCriteria = new BookingRequestConvertCriteriaDTO();
		List<BookingRequestDTO> bookingRequestDTOs = this.convertBookingRequestsToBookingRequestDTOs(bookingRequestsList,convertCriteria);
		
		BookingRequestPageDTO bookingRequestPageDTO = new BookingRequestPageDTO();
		bookingRequestPageDTO.setBookingRequests(bookingRequestDTOs);
		bookingRequestPageDTO.setTotalElements(bookingRequests.getTotalElements());
		return ResponseEntity.ok(bookingRequestPageDTO);
	}

	public List<BookingRequestDTO> convertBookingRequestsToBookingRequestDTOs(List<BookingRequest> bookingRequests, BookingRequestConvertCriteriaDTO convertCriteria) {
		
		List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<BookingRequestDTO>();
		
		for (BookingRequest bookingRequest : bookingRequests) {
			bookingRequestDTOs.add(convertBookingRequestToBookingRequestDTO(bookingRequest,convertCriteria));
		}
		
		return bookingRequestDTOs;

	}
	
	public BookingRequestDTO convertBookingRequestToBookingRequestDTO(BookingRequest bookingRequest, BookingRequestConvertCriteriaDTO convertCriteria) {
		
		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		
		bookingRequestDTO.setBookingRequestId(bookingRequest.getBookingRequestId());

	
		bookingRequestDTO.setRequestDate(bookingRequest.getRequestDate());

	

		
		return bookingRequestDTO;
	}

	public ResultDTO updateBookingRequest(BookingRequestDTO bookingRequestDTO, RequestDTO requestDTO) {
		
		BookingRequest bookingRequest = bookingRequestDao.getById(bookingRequestDTO.getBookingRequestId());

		bookingRequest.setBookingRequestId(ControllerUtils.setValue(bookingRequest.getBookingRequestId(), bookingRequestDTO.getBookingRequestId()));

		bookingRequest.setRequestDate(ControllerUtils.setValue(bookingRequest.getRequestDate(), bookingRequestDTO.getRequestDate()));



        bookingRequest = bookingRequestDao.save(bookingRequest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BookingRequestDTO getBookingRequestDTOById(Integer bookingRequestId) {
	
		BookingRequest bookingRequest = bookingRequestDao.getById(bookingRequestId);
			
		
		BookingRequestConvertCriteriaDTO convertCriteria = new BookingRequestConvertCriteriaDTO();
		return(this.convertBookingRequestToBookingRequestDTO(bookingRequest,convertCriteria));
	}







}
