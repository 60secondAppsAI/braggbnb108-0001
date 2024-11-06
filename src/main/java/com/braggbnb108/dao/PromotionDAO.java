package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


