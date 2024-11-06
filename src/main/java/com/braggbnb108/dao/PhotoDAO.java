package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Photo;





public interface PhotoDAO extends GenericDAO<Photo, Integer> {
  
	List<Photo> findAll();
	






}


