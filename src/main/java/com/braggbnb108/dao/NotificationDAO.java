package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


