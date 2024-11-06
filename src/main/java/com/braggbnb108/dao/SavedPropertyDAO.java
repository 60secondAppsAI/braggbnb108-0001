package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.SavedProperty;





public interface SavedPropertyDAO extends GenericDAO<SavedProperty, Integer> {
  
	List<SavedProperty> findAll();
	






}


