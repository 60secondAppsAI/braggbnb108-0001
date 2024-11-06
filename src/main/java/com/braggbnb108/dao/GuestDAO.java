package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Guest;





public interface GuestDAO extends GenericDAO<Guest, Integer> {
  
	List<Guest> findAll();
	






}


