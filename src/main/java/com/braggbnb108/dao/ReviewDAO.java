package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Review;





public interface ReviewDAO extends GenericDAO<Review, Integer> {
  
	List<Review> findAll();
	






}


