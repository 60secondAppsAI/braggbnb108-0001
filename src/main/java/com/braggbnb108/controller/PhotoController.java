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

import com.braggbnb108.domain.Photo;
import com.braggbnb108.dto.PhotoDTO;
import com.braggbnb108.dto.PhotoSearchDTO;
import com.braggbnb108.dto.PhotoPageDTO;
import com.braggbnb108.service.PhotoService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/photo")
@RestController
public class PhotoController {

	private final static Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	PhotoService photoService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Photo> getAll() {

		List<Photo> photos = photoService.findAll();
		
		return photos;	
	}

	@GetMapping(value = "/{photoId}")
	@ResponseBody
	public PhotoDTO getPhoto(@PathVariable Integer photoId) {
		
		return (photoService.getPhotoDTOById(photoId));
	}

 	@RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
	public ResponseEntity<?> addPhoto(@RequestBody PhotoDTO photoDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = photoService.addPhoto(photoDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/photos")
	public ResponseEntity<PhotoPageDTO> getPhotos(PhotoSearchDTO photoSearchDTO) {
 
		return photoService.getPhotos(photoSearchDTO);
	}	

	@RequestMapping(value = "/updatePhoto", method = RequestMethod.POST)
	public ResponseEntity<?> updatePhoto(@RequestBody PhotoDTO photoDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = photoService.updatePhoto(photoDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
