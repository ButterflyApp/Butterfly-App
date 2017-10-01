package com.butterfly.butterflyapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.butterfly.butterflyapp.domain.User;
import com.butterfly.butterflyapp.repository.UserRepository;
import com.butterfly.butterflyapp.service.NewUserService;

@Service
public class NewUserServiceImpl implements NewUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByLogin(String login) {
		
		return userRepository.findByLogin(login);
	}

	@Override
	public User findOne(Long id) {
		
		return userRepository.findOne(id);
	}

}
