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

import com.braggbnb108.domain.Amenity;
import com.braggbnb108.dto.AmenityDTO;
import com.braggbnb108.dto.AmenitySearchDTO;
import com.braggbnb108.dto.AmenityPageDTO;
import com.braggbnb108.service.AmenityService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/amenity")
@RestController
public class AmenityController {

	private final static Logger logger = LoggerFactory.getLogger(AmenityController.class);

	@Autowired
	AmenityService amenityService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Amenity> getAll() {

		List<Amenity> amenitys = amenityService.findAll();
		
		return amenitys;	
	}

	@GetMapping(value = "/{amenityId}")
	@ResponseBody
	public AmenityDTO getAmenity(@PathVariable Integer amenityId) {
		
		return (amenityService.getAmenityDTOById(amenityId));
	}

 	@RequestMapping(value = "/addAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> addAmenity(@RequestBody AmenityDTO amenityDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = amenityService.addAmenity(amenityDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/amenitys")
	public ResponseEntity<AmenityPageDTO> getAmenitys(AmenitySearchDTO amenitySearchDTO) {
 
		return amenityService.getAmenitys(amenitySearchDTO);
	}	

	@RequestMapping(value = "/updateAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> updateAmenity(@RequestBody AmenityDTO amenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = amenityService.updateAmenity(amenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
