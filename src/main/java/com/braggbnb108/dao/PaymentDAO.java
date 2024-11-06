package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


