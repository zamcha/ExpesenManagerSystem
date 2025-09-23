package com.vti.service;

import com.vti.dto.WalletCreateRequest;
import com.vti.entity.Wallet;
import com.vti.entity.User;
import com.vti.form.WalletFilterForm;
import com.vti.mapper.WalletMapper;
import com.vti.repository.UserRepository;
import com.vti.repository.WalletRepository;
import com.vti.specification.WalletSpecification;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService implements IWalletService {

	private final WalletRepository walletRepository;

	private final UserRepository userRepository;
	private final WalletMapper walletMapper;
	private final ModelMapper modelMapper;

	@SuppressWarnings("null")
	public void createWallet(WalletCreateRequest form) {
		Wallet wallet = walletMapper.toEntity(form);

		Optional<User> userOptional = userRepository.findById(form.getOwnerId());
		User user = userOptional.get();
		wallet.setOwner(user);
		walletRepository.save(wallet);
	}

	public Wallet getWalletById(Long id) {
		return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ví"));
	}

	public Wallet updateWallet(Long id, Wallet walletDetails) {
		Wallet wallet = getWalletById(id);
		wallet.setName(walletDetails.getName());
		wallet.setBalance(walletDetails.getBalance());
		return walletRepository.save(wallet);
	}

	public void deleteWallet(Long id) {
		walletRepository.deleteById(id);
	}

	@Override
	public Page<Wallet> getAllWallets(Pageable pageable, String search, WalletFilterForm filterForm) {

		Specification<Wallet> where = WalletSpecification.buildWhere(search, filterForm);
		return walletRepository.findAll(where, pageable);
	}
}