package com.butterfly.butterflyapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butterfly.butterflyapp.domain.User;
import com.butterfly.butterflyapp.service.NewUserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private NewUserService userService;

  @GetMapping("/user/save/{login}")
  public User saveUser(@PathVariable String login,HttpSession session) {
	  User user =userService.findByLogin(login);
	  session.setAttribute("userId", user.getId());
	  
	  return user;
  }
	
}
