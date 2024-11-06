package com.braggbnb108.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbnb108.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbnb108.domain.BookingRequest;
import com.braggbnb108.dto.BookingRequestDTO;
import com.braggbnb108.dto.BookingRequestSearchDTO;
import com.braggbnb108.dto.BookingRequestPageDTO;
import com.braggbnb108.service.BookingRequestService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/bookingRequest")
@RestController
public class BookingRequestController {

	private final static Logger logger = LoggerFactory.getLogger(BookingRequestController.class);

	@Autowired
	BookingRequestService bookingRequestService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<BookingRequest> getAll() {

		List<BookingRequest> bookingRequests = bookingRequestService.findAll();
		
		return bookingRequests;	
	}

	@GetMapping(value = "/{bookingRequestId}")
	@ResponseBody
	public BookingRequestDTO getBookingRequest(@PathVariable Integer bookingRequestId) {
		
		return (bookingRequestService.getBookingRequestDTOById(bookingRequestId));
	}

 	@RequestMapping(value = "/addBookingRequest", method = RequestMethod.POST)
	public ResponseEntity<?> addBookingRequest(@RequestBody BookingRequestDTO bookingRequestDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingRequestService.addBookingRequest(bookingRequestDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/bookingRequests")
	public ResponseEntity<BookingRequestPageDTO> getBookingRequests(BookingRequestSearchDTO bookingRequestSearchDTO) {
 
		return bookingRequestService.getBookingRequests(bookingRequestSearchDTO);
	}	

	@RequestMapping(value = "/updateBookingRequest", method = RequestMethod.POST)
	public ResponseEntity<?> updateBookingRequest(@RequestBody BookingRequestDTO bookingRequestDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = bookingRequestService.updateBookingRequest(bookingRequestDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
