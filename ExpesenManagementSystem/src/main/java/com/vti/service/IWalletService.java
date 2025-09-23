package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.WalletCreateRequest;
import com.vti.entity.Wallet;
import com.vti.form.WalletFilterForm;

public interface IWalletService {

	void createWallet(WalletCreateRequest wallet);

	Wallet getWalletById(Long id);

	Page<Wallet> getAllWallets(Pageable pageable,String search, WalletFilterForm filterForm);

	Wallet updateWallet(Long id, Wallet walletDetails);

	void deleteWallet(Long id);

}
