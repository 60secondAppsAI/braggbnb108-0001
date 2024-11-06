package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.HostReview;





public interface HostReviewDAO extends GenericDAO<HostReview, Integer> {
  
	List<HostReview> findAll();
	






}


