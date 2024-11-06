package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Amenity;





public interface AmenityDAO extends GenericDAO<Amenity, Integer> {
  
	List<Amenity> findAll();
	






}


