package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Availability;





public interface AvailabilityDAO extends GenericDAO<Availability, Integer> {
  
	List<Availability> findAll();
	






}


