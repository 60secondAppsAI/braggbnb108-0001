package com.braggbnb108.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.service.impl.GenericServiceImpl;
import com.braggbnb108.dao.PhotoDAO;
import com.braggbnb108.domain.Photo;
import com.braggbnb108.dto.PhotoDTO;
import com.braggbnb108.dto.PhotoSearchDTO;
import com.braggbnb108.dto.PhotoPageDTO;
import com.braggbnb108.dto.PhotoConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.PhotoService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class PhotoServiceImpl extends GenericServiceImpl<Photo, Integer> implements PhotoService {

    private final static Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

	@Autowired
	PhotoDAO photoDao;

	


	@Override
	public GenericDAO<Photo, Integer> getDAO() {
		return (GenericDAO<Photo, Integer>) photoDao;
	}
	
	public List<Photo> findAll () {
		List<Photo> photos = photoDao.findAll();
		
		return photos;	
		
	}

	public ResultDTO addPhoto(PhotoDTO photoDTO, RequestDTO requestDTO) {

		Photo photo = new Photo();

		photo.setPhotoId(photoDTO.getPhotoId());


		photo.setUrl(photoDTO.getUrl());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		photo = photoDao.save(photo);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Photo> getAllPhotos(Pageable pageable) {
		return photoDao.findAll(pageable);
	}

	public Page<Photo> getAllPhotos(Specification<Photo> spec, Pageable pageable) {
		return photoDao.findAll(spec, pageable);
	}

	public ResponseEntity<PhotoPageDTO> getPhotos(PhotoSearchDTO photoSearchDTO) {
	
			Integer photoId = photoSearchDTO.getPhotoId(); 
 			String url = photoSearchDTO.getUrl(); 
 			String sortBy = photoSearchDTO.getSortBy();
			String sortOrder = photoSearchDTO.getSortOrder();
			String searchQuery = photoSearchDTO.getSearchQuery();
			Integer page = photoSearchDTO.getPage();
			Integer size = photoSearchDTO.getSize();

	        Specification<Photo> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, photoId, "photoId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, url, "url"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("url")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Photo> photos = this.getAllPhotos(spec, pageable);
		
		//System.out.println(String.valueOf(photos.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(photos.getTotalPages()));
		
		List<Photo> photosList = photos.getContent();
		
		PhotoConvertCriteriaDTO convertCriteria = new PhotoConvertCriteriaDTO();
		List<PhotoDTO> photoDTOs = this.convertPhotosToPhotoDTOs(photosList,convertCriteria);
		
		PhotoPageDTO photoPageDTO = new PhotoPageDTO();
		photoPageDTO.setPhotos(photoDTOs);
		photoPageDTO.setTotalElements(photos.getTotalElements());
		return ResponseEntity.ok(photoPageDTO);
	}

	public List<PhotoDTO> convertPhotosToPhotoDTOs(List<Photo> photos, PhotoConvertCriteriaDTO convertCriteria) {
		
		List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();
		
		for (Photo photo : photos) {
			photoDTOs.add(convertPhotoToPhotoDTO(photo,convertCriteria));
		}
		
		return photoDTOs;

	}
	
	public PhotoDTO convertPhotoToPhotoDTO(Photo photo, PhotoConvertCriteriaDTO convertCriteria) {
		
		PhotoDTO photoDTO = new PhotoDTO();
		
		photoDTO.setPhotoId(photo.getPhotoId());

	
		photoDTO.setUrl(photo.getUrl());

	

		
		return photoDTO;
	}

	public ResultDTO updatePhoto(PhotoDTO photoDTO, RequestDTO requestDTO) {
		
		Photo photo = photoDao.getById(photoDTO.getPhotoId());

		photo.setPhotoId(ControllerUtils.setValue(photo.getPhotoId(), photoDTO.getPhotoId()));

		photo.setUrl(ControllerUtils.setValue(photo.getUrl(), photoDTO.getUrl()));



        photo = photoDao.save(photo);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PhotoDTO getPhotoDTOById(Integer photoId) {
	
		Photo photo = photoDao.getById(photoId);
			
		
		PhotoConvertCriteriaDTO convertCriteria = new PhotoConvertCriteriaDTO();
		return(this.convertPhotoToPhotoDTO(photo,convertCriteria));
	}







}
