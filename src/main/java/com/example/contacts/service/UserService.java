package com.example.contacts.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.contacts.dto.UserRegistrationDto;
import com.example.contacts.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
