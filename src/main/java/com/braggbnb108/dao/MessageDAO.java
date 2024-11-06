package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


