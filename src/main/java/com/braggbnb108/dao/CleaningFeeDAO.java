package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.CleaningFee;





public interface CleaningFeeDAO extends GenericDAO<CleaningFee, Integer> {
  
	List<CleaningFee> findAll();
	






}


