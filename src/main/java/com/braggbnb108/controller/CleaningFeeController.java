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

import com.braggbnb108.domain.CleaningFee;
import com.braggbnb108.dto.CleaningFeeDTO;
import com.braggbnb108.dto.CleaningFeeSearchDTO;
import com.braggbnb108.dto.CleaningFeePageDTO;
import com.braggbnb108.service.CleaningFeeService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/cleaningFee")
@RestController
public class CleaningFeeController {

	private final static Logger logger = LoggerFactory.getLogger(CleaningFeeController.class);

	@Autowired
	CleaningFeeService cleaningFeeService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CleaningFee> getAll() {

		List<CleaningFee> cleaningFees = cleaningFeeService.findAll();
		
		return cleaningFees;	
	}

	@GetMapping(value = "/{cleaningFeeId}")
	@ResponseBody
	public CleaningFeeDTO getCleaningFee(@PathVariable Integer cleaningFeeId) {
		
		return (cleaningFeeService.getCleaningFeeDTOById(cleaningFeeId));
	}

 	@RequestMapping(value = "/addCleaningFee", method = RequestMethod.POST)
	public ResponseEntity<?> addCleaningFee(@RequestBody CleaningFeeDTO cleaningFeeDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cleaningFeeService.addCleaningFee(cleaningFeeDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/cleaningFees")
	public ResponseEntity<CleaningFeePageDTO> getCleaningFees(CleaningFeeSearchDTO cleaningFeeSearchDTO) {
 
		return cleaningFeeService.getCleaningFees(cleaningFeeSearchDTO);
	}	

	@RequestMapping(value = "/updateCleaningFee", method = RequestMethod.POST)
	public ResponseEntity<?> updateCleaningFee(@RequestBody CleaningFeeDTO cleaningFeeDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cleaningFeeService.updateCleaningFee(cleaningFeeDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
