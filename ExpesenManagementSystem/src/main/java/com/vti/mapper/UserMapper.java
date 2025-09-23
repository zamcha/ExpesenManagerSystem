package com.vti.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vti.dto.UserCreateRequest;
import com.vti.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "status", constant = "ACTIVE")
	User toEntity(UserCreateRequest dto);
}
