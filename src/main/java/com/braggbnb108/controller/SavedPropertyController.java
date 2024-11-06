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

import com.braggbnb108.domain.SavedProperty;
import com.braggbnb108.dto.SavedPropertyDTO;
import com.braggbnb108.dto.SavedPropertySearchDTO;
import com.braggbnb108.dto.SavedPropertyPageDTO;
import com.braggbnb108.service.SavedPropertyService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/savedProperty")
@RestController
public class SavedPropertyController {

	private final static Logger logger = LoggerFactory.getLogger(SavedPropertyController.class);

	@Autowired
	SavedPropertyService savedPropertyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SavedProperty> getAll() {

		List<SavedProperty> savedPropertys = savedPropertyService.findAll();
		
		return savedPropertys;	
	}

	@GetMapping(value = "/{savedPropertyId}")
	@ResponseBody
	public SavedPropertyDTO getSavedProperty(@PathVariable Integer savedPropertyId) {
		
		return (savedPropertyService.getSavedPropertyDTOById(savedPropertyId));
	}

 	@RequestMapping(value = "/addSavedProperty", method = RequestMethod.POST)
	public ResponseEntity<?> addSavedProperty(@RequestBody SavedPropertyDTO savedPropertyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = savedPropertyService.addSavedProperty(savedPropertyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/savedPropertys")
	public ResponseEntity<SavedPropertyPageDTO> getSavedPropertys(SavedPropertySearchDTO savedPropertySearchDTO) {
 
		return savedPropertyService.getSavedPropertys(savedPropertySearchDTO);
	}	

	@RequestMapping(value = "/updateSavedProperty", method = RequestMethod.POST)
	public ResponseEntity<?> updateSavedProperty(@RequestBody SavedPropertyDTO savedPropertyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = savedPropertyService.updateSavedProperty(savedPropertyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
