package com.vti.repository;

import com.vti.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // Lấy ví của 1 user (có phân trang)
    Page<Wallet> findByUserId(Long userId, Pageable pageable);
}