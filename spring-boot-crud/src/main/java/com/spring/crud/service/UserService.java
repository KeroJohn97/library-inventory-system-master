package com.spring.crud.service;

import com.spring.crud.model.dto.UserRegistrationDto;
import com.spring.crud.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
	List<User> getAll();
}
