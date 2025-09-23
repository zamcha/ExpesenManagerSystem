package com.vti.repository;

import com.vti.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    // Lấy category của 1 ví (có phân trang)
    Page<Category> findByWalletId(Long walletId, Pageable pageable);
}