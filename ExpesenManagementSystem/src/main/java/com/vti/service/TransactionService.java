package com.vti.service;

import com.vti.dto.CategoryResponse;
import com.vti.dto.TransactionCreateRequest;
import com.vti.entity.Category;
import com.vti.entity.Transaction;
import com.vti.entity.Transaction.Type;
import com.vti.entity.User;
import com.vti.entity.Wallet;
import com.vti.form.TransactionFilterForm;
import com.vti.mapper.TransactionMapper;
import com.vti.repository.CategoryRepository;
import com.vti.repository.TransactionRepository;
import com.vti.repository.UserRepository;
import com.vti.repository.WalletRepository;
import com.vti.specification.TransactionSpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {

	private final TransactionRepository transactionRepository;
	private final WalletRepository walletRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final TransactionMapper mapper;

	@Override
	public void createTransaction(TransactionCreateRequest form) {
	    // Chuyển String → Enum an toàn
	    Type type;
	    try {
	        type = Type.valueOf(form.getType().toUpperCase());
	    } catch (IllegalArgumentException e) {
	        throw new RuntimeException("Loại giao dịch không hợp lệ: " + form.getType());
	    }

	    // Lấy các entity từ DB
	    User user = userRepository.findById(form.getUserId())
	        .orElseThrow(() -> new RuntimeException("User không tồn tại"));

	    Wallet wallet = walletRepository.findById(form.getWalletId())
	        .orElseThrow(() -> new RuntimeException("Wallet không tồn tại"));

	    Category category = categoryRepository.findById(form.getCategoryId())
	        .orElseThrow(() -> new RuntimeException("Category không tồn tại"));

	    BigDecimal amount = form.getAmount();

	    // Xử lý số dư ví
	    if (type == Type.EXPENSE) {
	        if (wallet.getBalance().compareTo(amount) < 0) {
	            throw new IllegalArgumentException("Số dư không đủ");
	        }
	        wallet.setBalance(wallet.getBalance().subtract(amount));
	    } else if (type == Type.INCOME) {
	        wallet.setBalance(wallet.getBalance().add(amount));
	    }

	    // Lưu ví để cập nhật số dư và updatedAt
	    walletRepository.save(wallet);

	    // Tạo transaction
	    Transaction transaction = mapper.toEntity(form);
	    transaction.setUser(user);
	    transaction.setWallet(wallet);
	    transaction.setCategory(category);

	    transactionRepository.save(transaction);
	}

	public Transaction getTransaction(Long id) {
		return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch"));
	}

	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public Page<Transaction> getAllTransaction(Pageable pageable, String search, TransactionFilterForm filterForm) {
		Specification<Transaction> where = TransactionSpecification.buildWhere(search, filterForm);
		return transactionRepository.findAll(where, pageable);
	}
}