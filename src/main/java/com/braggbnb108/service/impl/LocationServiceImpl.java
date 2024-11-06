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
import com.braggbnb108.dao.LocationDAO;
import com.braggbnb108.domain.Location;
import com.braggbnb108.dto.LocationDTO;
import com.braggbnb108.dto.LocationSearchDTO;
import com.braggbnb108.dto.LocationPageDTO;
import com.braggbnb108.dto.LocationConvertCriteriaDTO;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import com.braggbnb108.service.LocationService;
import com.braggbnb108.util.ControllerUtils;





@Service
public class LocationServiceImpl extends GenericServiceImpl<Location, Integer> implements LocationService {

    private final static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

	@Autowired
	LocationDAO locationDao;

	


	@Override
	public GenericDAO<Location, Integer> getDAO() {
		return (GenericDAO<Location, Integer>) locationDao;
	}
	
	public List<Location> findAll () {
		List<Location> locations = locationDao.findAll();
		
		return locations;	
		
	}

	public ResultDTO addLocation(LocationDTO locationDTO, RequestDTO requestDTO) {

		Location location = new Location();

		location.setLocationId(locationDTO.getLocationId());


		location.setCity(locationDTO.getCity());


		location.setState(locationDTO.getState());


		location.setCountry(locationDTO.getCountry());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		location = locationDao.save(location);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Location> getAllLocations(Pageable pageable) {
		return locationDao.findAll(pageable);
	}

	public Page<Location> getAllLocations(Specification<Location> spec, Pageable pageable) {
		return locationDao.findAll(spec, pageable);
	}

	public ResponseEntity<LocationPageDTO> getLocations(LocationSearchDTO locationSearchDTO) {
	
			Integer locationId = locationSearchDTO.getLocationId(); 
 			String city = locationSearchDTO.getCity(); 
 			String state = locationSearchDTO.getState(); 
 			String country = locationSearchDTO.getCountry(); 
 			String sortBy = locationSearchDTO.getSortBy();
			String sortOrder = locationSearchDTO.getSortOrder();
			String searchQuery = locationSearchDTO.getSearchQuery();
			Integer page = locationSearchDTO.getPage();
			Integer size = locationSearchDTO.getSize();

	        Specification<Location> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, locationId, "locationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			
			spec = ControllerUtils.andIfNecessary(spec, state, "state"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("state")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Location> locations = this.getAllLocations(spec, pageable);
		
		//System.out.println(String.valueOf(locations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(locations.getTotalPages()));
		
		List<Location> locationsList = locations.getContent();
		
		LocationConvertCriteriaDTO convertCriteria = new LocationConvertCriteriaDTO();
		List<LocationDTO> locationDTOs = this.convertLocationsToLocationDTOs(locationsList,convertCriteria);
		
		LocationPageDTO locationPageDTO = new LocationPageDTO();
		locationPageDTO.setLocations(locationDTOs);
		locationPageDTO.setTotalElements(locations.getTotalElements());
		return ResponseEntity.ok(locationPageDTO);
	}

	public List<LocationDTO> convertLocationsToLocationDTOs(List<Location> locations, LocationConvertCriteriaDTO convertCriteria) {
		
		List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
		
		for (Location location : locations) {
			locationDTOs.add(convertLocationToLocationDTO(location,convertCriteria));
		}
		
		return locationDTOs;

	}
	
	public LocationDTO convertLocationToLocationDTO(Location location, LocationConvertCriteriaDTO convertCriteria) {
		
		LocationDTO locationDTO = new LocationDTO();
		
		locationDTO.setLocationId(location.getLocationId());

	
		locationDTO.setCity(location.getCity());

	
		locationDTO.setState(location.getState());

	
		locationDTO.setCountry(location.getCountry());

	

		
		return locationDTO;
	}

	public ResultDTO updateLocation(LocationDTO locationDTO, RequestDTO requestDTO) {
		
		Location location = locationDao.getById(locationDTO.getLocationId());

		location.setLocationId(ControllerUtils.setValue(location.getLocationId(), locationDTO.getLocationId()));

		location.setCity(ControllerUtils.setValue(location.getCity(), locationDTO.getCity()));

		location.setState(ControllerUtils.setValue(location.getState(), locationDTO.getState()));

		location.setCountry(ControllerUtils.setValue(location.getCountry(), locationDTO.getCountry()));



        location = locationDao.save(location);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LocationDTO getLocationDTOById(Integer locationId) {
	
		Location location = locationDao.getById(locationId);
			
		
		LocationConvertCriteriaDTO convertCriteria = new LocationConvertCriteriaDTO();
		return(this.convertLocationToLocationDTO(location,convertCriteria));
	}







}
