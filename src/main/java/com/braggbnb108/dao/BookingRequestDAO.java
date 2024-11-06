package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.BookingRequest;





public interface BookingRequestDAO extends GenericDAO<BookingRequest, Integer> {
  
	List<BookingRequest> findAll();
	






}


