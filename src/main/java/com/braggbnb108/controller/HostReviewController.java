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

import com.braggbnb108.domain.HostReview;
import com.braggbnb108.dto.HostReviewDTO;
import com.braggbnb108.dto.HostReviewSearchDTO;
import com.braggbnb108.dto.HostReviewPageDTO;
import com.braggbnb108.service.HostReviewService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/hostReview")
@RestController
public class HostReviewController {

	private final static Logger logger = LoggerFactory.getLogger(HostReviewController.class);

	@Autowired
	HostReviewService hostReviewService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<HostReview> getAll() {

		List<HostReview> hostReviews = hostReviewService.findAll();
		
		return hostReviews;	
	}

	@GetMapping(value = "/{hostReviewId}")
	@ResponseBody
	public HostReviewDTO getHostReview(@PathVariable Integer hostReviewId) {
		
		return (hostReviewService.getHostReviewDTOById(hostReviewId));
	}

 	@RequestMapping(value = "/addHostReview", method = RequestMethod.POST)
	public ResponseEntity<?> addHostReview(@RequestBody HostReviewDTO hostReviewDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostReviewService.addHostReview(hostReviewDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/hostReviews")
	public ResponseEntity<HostReviewPageDTO> getHostReviews(HostReviewSearchDTO hostReviewSearchDTO) {
 
		return hostReviewService.getHostReviews(hostReviewSearchDTO);
	}	

	@RequestMapping(value = "/updateHostReview", method = RequestMethod.POST)
	public ResponseEntity<?> updateHostReview(@RequestBody HostReviewDTO hostReviewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostReviewService.updateHostReview(hostReviewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
