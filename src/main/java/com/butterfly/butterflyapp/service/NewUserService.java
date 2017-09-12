package com.butterfly.butterflyapp.service;

import org.springframework.stereotype.Service;

import com.butterfly.butterflyapp.domain.User;

@Service
public interface NewUserService {

	public User findByLogin(String login);
}
