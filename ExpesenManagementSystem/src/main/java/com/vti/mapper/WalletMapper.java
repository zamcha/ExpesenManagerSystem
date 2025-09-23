package com.vti.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vti.dto.WalletCreateRequest;
import com.vti.entity.Wallet;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(source = "initalBalance", target = "balance")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "id", ignore = true)
    Wallet toEntity(WalletCreateRequest dto);
}

