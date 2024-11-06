package com.braggbnb108.dao;

import java.util.List;

import com.braggbnb108.dao.GenericDAO;
import com.braggbnb108.domain.SupportTicket;





public interface SupportTicketDAO extends GenericDAO<SupportTicket, Integer> {
  
	List<SupportTicket> findAll();
	






}


