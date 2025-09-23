package com.vti.service;

import com.vti.dto.UserCreateRequest;
import com.vti.entity.Role;
import com.vti.entity.User;
import com.vti.form.UserFilterForm;
import com.vti.mapper.UserMapper;
import com.vti.repository.RoleRepository;
import com.vti.repository.UserRepository;
import com.vti.specification.UserSpecification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	private final UserMapper mapper;

//    public User updateUser(Long id, User userDetails) {
//        User user = getUser(id);
//        user.setEmail(userDetails.getEmail());
//        if (userDetails.getPasswordHash() != null) {
//            user.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));
//        }
//        return userRepository.save(user);
//    }

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Page<User> getAllUsers(Pageable pageable, String search, UserFilterForm filterForm) {

		Specification<User> where = UserSpecification.buildWhere(search, filterForm);

		return userRepository.findAll(where, pageable);
	}

	@Override
	public Optional<User> getUserByID(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	@Override
	public void createUser(UserCreateRequest form) {
		String roleName = form.getRoleName().toUpperCase();
		Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("User không tồn tại"));
		User user = mapper.toEntity(form);
		user.getRoles().add(role);
		userRepository.save(user);
	}

}