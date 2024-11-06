package com.braggbnb108.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb108.domain.Notification;
import com.braggbnb108.dto.NotificationDTO;
import com.braggbnb108.dto.NotificationSearchDTO;
import com.braggbnb108.dto.NotificationPageDTO;
import com.braggbnb108.dto.NotificationConvertCriteriaDTO;
import com.braggbnb108.service.GenericService;
import com.braggbnb108.dto.common.RequestDTO;
import com.braggbnb108.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NotificationService extends GenericService<Notification, Integer> {

	List<Notification> findAll();

	ResultDTO addNotification(NotificationDTO notificationDTO, RequestDTO requestDTO);

	ResultDTO updateNotification(NotificationDTO notificationDTO, RequestDTO requestDTO);

    Page<Notification> getAllNotifications(Pageable pageable);

    Page<Notification> getAllNotifications(Specification<Notification> spec, Pageable pageable);

	ResponseEntity<NotificationPageDTO> getNotifications(NotificationSearchDTO notificationSearchDTO);
	
	List<NotificationDTO> convertNotificationsToNotificationDTOs(List<Notification> notifications, NotificationConvertCriteriaDTO convertCriteria);

	NotificationDTO getNotificationDTOById(Integer notificationId);







}





