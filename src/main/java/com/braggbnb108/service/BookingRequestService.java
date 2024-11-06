package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.BookingRequest;
import com.braggbnb108.dto.BookingRequestDTO;
import com.braggbnb108.dto.BookingRequestSearchDTO;
import com.braggbnb108.dto.BookingRequestPageDTO;
import com.braggbnb108.dto.BookingRequestConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BookingRequestService extends GenericService<BookingRequest, Integer> {

	List<BookingRequest> findAll();

	ResultDTO addBookingRequest(BookingRequestDTO bookingRequestDTO, RequestDTO requestDTO);

	ResultDTO updateBookingRequest(BookingRequestDTO bookingRequestDTO, RequestDTO requestDTO);

    Page<BookingRequest> getAllBookingRequests(Pageable pageable);

    Page<BookingRequest> getAllBookingRequests(Specification<BookingRequest> spec, Pageable pageable);

	ResponseEntity<BookingRequestPageDTO> getBookingRequests(BookingRequestSearchDTO bookingRequestSearchDTO);
	
	List<BookingRequestDTO> convertBookingRequestsToBookingRequestDTOs(List<BookingRequest> bookingRequests, BookingRequestConvertCriteriaDTO convertCriteria);

	BookingRequestDTO getBookingRequestDTOById(Integer bookingRequestId);







}





