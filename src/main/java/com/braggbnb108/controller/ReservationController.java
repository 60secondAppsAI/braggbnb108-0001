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

import com.braggbnb108.domain.Reservation;
import com.braggbnb108.dto.ReservationDTO;
import com.braggbnb108.dto.ReservationSearchDTO;
import com.braggbnb108.dto.ReservationPageDTO;
import com.braggbnb108.service.ReservationService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/reservation")
@RestController
public class ReservationController {

	private final static Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService reservationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Reservation> getAll() {

		List<Reservation> reservations = reservationService.findAll();
		
		return reservations;	
	}

	@GetMapping(value = "/{reservationId}")
	@ResponseBody
	public ReservationDTO getReservation(@PathVariable Integer reservationId) {
		
		return (reservationService.getReservationDTOById(reservationId));
	}

 	@RequestMapping(value = "/addReservation", method = RequestMethod.POST)
	public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationService.addReservation(reservationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/reservations")
	public ResponseEntity<ReservationPageDTO> getReservations(ReservationSearchDTO reservationSearchDTO) {
 
		return reservationService.getReservations(reservationSearchDTO);
	}	

	@RequestMapping(value = "/updateReservation", method = RequestMethod.POST)
	public ResponseEntity<?> updateReservation(@RequestBody ReservationDTO reservationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationService.updateReservation(reservationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
