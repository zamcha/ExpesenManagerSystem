package com.vti.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.UserCreateRequest;
import com.vti.entity.User;
import com.vti.form.UserFilterForm;

public interface IUserService {

	Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filterForm);
	
	Optional<User> getUserByID(Long id);
	
	void deleteUser(Long id);
	
	void createUser(UserCreateRequest form);
}
