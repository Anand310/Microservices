package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "handleContactDownTime")
	public User getUser(@PathVariable ("userId") Long userId) {
		User user = this.userService.getUser(userId);
		List contact = this.restTemplate.getForObject("http://contact-service:7072/contact/user/"+user.getUserId(), List.class);
		user.setContact(contact);
		return user;
		
	}
	
//	@GetMapping("/{userId}")
	public User handleContactDownTime(@PathVariable ("userId") Long userId) {
		User user = this.userService.getUser(userId);
//		List contact = this.restTemplate.getForObject("http://contact-service:7072/contact/user/"+user.getUserId(), List.class);
//		user.setContact(contact);
		return user;
		
	}
	
}
