package com.user.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

	List<User> list = Arrays.asList(
			new User(2001L,"Anand","9955025621"),
			new User(2002L,"Subu","8855025621"),
			new User(2003L,"Shivam","7755025621")
	);
	
	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return this.list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
	}
	

}
