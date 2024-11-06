package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Host;





public interface HostDAO extends GenericDAO<Host, Integer> {
  
	List<Host> findAll();
	






}


