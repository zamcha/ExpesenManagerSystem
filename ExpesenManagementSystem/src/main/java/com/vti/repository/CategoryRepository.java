package com.vti.repository;

import com.vti.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Lấy category của 1 ví (có phân trang)
    Page<Category> findByWalletId(Long walletId, Pageable pageable);
}