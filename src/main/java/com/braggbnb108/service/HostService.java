package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.Host;
import com.braggbnb108.dto.HostDTO;
import com.braggbnb108.dto.HostSearchDTO;
import com.braggbnb108.dto.HostPageDTO;
import com.braggbnb108.dto.HostConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostService extends GenericService<Host, Integer> {

	List<Host> findAll();

	ResultDTO addHost(HostDTO hostDTO, RequestDTO requestDTO);

	ResultDTO updateHost(HostDTO hostDTO, RequestDTO requestDTO);

    Page<Host> getAllHosts(Pageable pageable);

    Page<Host> getAllHosts(Specification<Host> spec, Pageable pageable);

	ResponseEntity<HostPageDTO> getHosts(HostSearchDTO hostSearchDTO);
	
	List<HostDTO> convertHostsToHostDTOs(List<Host> hosts, HostConvertCriteriaDTO convertCriteria);

	HostDTO getHostDTOById(Integer hostId);







}





