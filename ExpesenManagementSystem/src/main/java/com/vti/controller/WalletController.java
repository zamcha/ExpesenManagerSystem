package com.vti.controller;

import com.vti.dto.WalletCreateRequest;
import com.vti.dto.WalletResponse;
import com.vti.entity.Wallet;
import com.vti.form.WalletFilterForm;
import com.vti.service.IWalletService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

	private final IWalletService walletService;

	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<String> createWallet(@RequestBody WalletCreateRequest form) {
		walletService.createWallet(form);
		return ResponseEntity.ok("Thêm ví thành công");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
		return ResponseEntity.ok(walletService.getWalletById(id));
	}

	@GetMapping
	public Page<WalletResponse> getAllWallets(Pageable pageable,
			@RequestParam(value = "search", required = false) String search, WalletFilterForm filterForm) {
		Page<Wallet> entityPages = walletService.getAllWallets(pageable, search, filterForm);
		List<WalletResponse> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<WalletResponse>>() {
		}.getType());
		Page<WalletResponse> dtosPage = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());
		return dtosPage;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
		return ResponseEntity.ok(walletService.updateWallet(id, wallet));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
		walletService.deleteWallet(id);
		return ResponseEntity.noContent().build();
	}
}