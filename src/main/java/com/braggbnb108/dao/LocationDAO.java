package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Location;





public interface LocationDAO extends GenericDAO<Location, Integer> {
  
	List<Location> findAll();
	






}


