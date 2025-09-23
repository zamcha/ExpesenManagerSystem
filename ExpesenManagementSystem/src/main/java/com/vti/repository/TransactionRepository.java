package com.vti.repository;

import com.vti.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    // Lấy giao dịch của 1 ví (có phân trang)
    Page<Transaction> findByWalletId(Long walletId, Pageable pageable);

    // Lấy giao dịch theo khoảng thời gian (có phân trang)
    Page<Transaction> findByWalletIdAndOccurredAtBetween(
            Long walletId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );
}