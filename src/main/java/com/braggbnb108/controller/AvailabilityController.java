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

import com.braggbnb108.domain.Availability;
import com.braggbnb108.dto.AvailabilityDTO;
import com.braggbnb108.dto.AvailabilitySearchDTO;
import com.braggbnb108.dto.AvailabilityPageDTO;
import com.braggbnb108.service.AvailabilityService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/availability")
@RestController
public class AvailabilityController {

	private final static Logger logger = LoggerFactory.getLogger(AvailabilityController.class);

	@Autowired
	AvailabilityService availabilityService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Availability> getAll() {

		List<Availability> availabilitys = availabilityService.findAll();
		
		return availabilitys;	
	}

	@GetMapping(value = "/{availabilityId}")
	@ResponseBody
	public AvailabilityDTO getAvailability(@PathVariable Integer availabilityId) {
		
		return (availabilityService.getAvailabilityDTOById(availabilityId));
	}

 	@RequestMapping(value = "/addAvailability", method = RequestMethod.POST)
	public ResponseEntity<?> addAvailability(@RequestBody AvailabilityDTO availabilityDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = availabilityService.addAvailability(availabilityDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/availabilitys")
	public ResponseEntity<AvailabilityPageDTO> getAvailabilitys(AvailabilitySearchDTO availabilitySearchDTO) {
 
		return availabilityService.getAvailabilitys(availabilitySearchDTO);
	}	

	@RequestMapping(value = "/updateAvailability", method = RequestMethod.POST)
	public ResponseEntity<?> updateAvailability(@RequestBody AvailabilityDTO availabilityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = availabilityService.updateAvailability(availabilityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
