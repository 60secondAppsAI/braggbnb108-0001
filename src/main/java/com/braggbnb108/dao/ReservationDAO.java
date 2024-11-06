package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Reservation;





public interface ReservationDAO extends GenericDAO<Reservation, Integer> {
  
	List<Reservation> findAll();
	






}


