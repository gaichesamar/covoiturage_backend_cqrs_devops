package com.example.notification;

import com.example.notification.entitie.Notification;
import com.example.notification.repositorie.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/commands")
@Slf4j
public class NotificationApplication {
	@Autowired
	private NotificationRepository notificationRepository;
	private List<String> notifications = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}


	@GetMapping("/notifications")
	public List<String> getNotifications() {
		List<Notification> notificationList = notificationRepository.findAll();
		List<String> messageList = new ArrayList<>();
		for (Notification notification : notificationList) {
			messageList.add(notification.getMessage());
		}
		return messageList;
	}



}
