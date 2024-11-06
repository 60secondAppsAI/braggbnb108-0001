package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.CancellationPolicy;





public interface CancellationPolicyDAO extends GenericDAO<CancellationPolicy, Integer> {
  
	List<CancellationPolicy> findAll();
	






}


