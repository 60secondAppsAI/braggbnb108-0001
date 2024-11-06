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

import com.braggbnb108.domain.SecurityDeposit;
import com.braggbnb108.dto.SecurityDepositDTO;
import com.braggbnb108.dto.SecurityDepositSearchDTO;
import com.braggbnb108.dto.SecurityDepositPageDTO;
import com.braggbnb108.service.SecurityDepositService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/securityDeposit")
@RestController
public class SecurityDepositController {

	private final static Logger logger = LoggerFactory.getLogger(SecurityDepositController.class);

	@Autowired
	SecurityDepositService securityDepositService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SecurityDeposit> getAll() {

		List<SecurityDeposit> securityDeposits = securityDepositService.findAll();
		
		return securityDeposits;	
	}

	@GetMapping(value = "/{securityDepositId}")
	@ResponseBody
	public SecurityDepositDTO getSecurityDeposit(@PathVariable Integer securityDepositId) {
		
		return (securityDepositService.getSecurityDepositDTOById(securityDepositId));
	}

 	@RequestMapping(value = "/addSecurityDeposit", method = RequestMethod.POST)
	public ResponseEntity<?> addSecurityDeposit(@RequestBody SecurityDepositDTO securityDepositDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = securityDepositService.addSecurityDeposit(securityDepositDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/securityDeposits")
	public ResponseEntity<SecurityDepositPageDTO> getSecurityDeposits(SecurityDepositSearchDTO securityDepositSearchDTO) {
 
		return securityDepositService.getSecurityDeposits(securityDepositSearchDTO);
	}	

	@RequestMapping(value = "/updateSecurityDeposit", method = RequestMethod.POST)
	public ResponseEntity<?> updateSecurityDeposit(@RequestBody SecurityDepositDTO securityDepositDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = securityDepositService.updateSecurityDeposit(securityDepositDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
