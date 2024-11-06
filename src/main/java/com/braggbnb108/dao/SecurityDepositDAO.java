package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.SecurityDeposit;





public interface SecurityDepositDAO extends GenericDAO<SecurityDeposit, Integer> {
  
	List<SecurityDeposit> findAll();
	






}


