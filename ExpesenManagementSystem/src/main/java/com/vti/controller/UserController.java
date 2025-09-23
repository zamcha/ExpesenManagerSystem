package com.vti.controller;

import com.vti.dto.UserCreateRequest;
import com.vti.dto.UserResponse;
import com.vti.entity.User;
import com.vti.form.UserFilterForm;
import com.vti.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

	private final UserService userService;

	private final ModelMapper modelMapper;

	@GetMapping
	public Page<UserResponse> getAllUsers(Pageable pageable, @RequestParam(value = "search",required = false) String search, UserFilterForm filterForm) {
		Page<User> entityPages = userService.getAllUsers(pageable,search, filterForm);

		List<UserResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<UserResponse>>() {
		}.getType());
		
		Page<UserResponse> dtosPage = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
		return dtosPage;
	}

	@GetMapping("/{id}")
	public UserResponse  getUserByID(@PathVariable Long id) {
		Optional<User> entities = userService.getUserByID(id);

		UserResponse dtos = modelMapper.map(entities, UserResponse.class);
		
		return dtos;
	}
	
	@PostMapping
	public ResponseEntity<String> createWallet(@RequestBody @Valid UserCreateRequest form) {
		userService.createUser(form);
		return ResponseEntity.ok("Tạo mới user thành công");
	}

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        return ResponseEntity.ok(userService.updateUser(id, user));
//    }

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}