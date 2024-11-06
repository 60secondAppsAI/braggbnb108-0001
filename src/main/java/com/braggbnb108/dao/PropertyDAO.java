package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Property;





public interface PropertyDAO extends GenericDAO<Property, Integer> {
  
	List<Property> findAll();
	






}


