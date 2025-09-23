package com.vti.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vti.dto.TransactionCreateRequest;
import com.vti.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
	@Mapping(target = "wallet", ignore = true)
	@Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "id", ignore = true)
	Transaction toEntity(TransactionCreateRequest dtos);
}
