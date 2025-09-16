package com.vti.repository;

import com.vti.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo email (dùng cho đăng nhập)
    Optional<User> findByEmail(String email);

    // Kiểm tra email đã tồn tại chưa
    boolean existsByEmail(String email);

    // Lấy tất cả user có phân trang
    Page<User> findAll(Pageable pageable);

    // Tìm user theo tên (có phân trang)
    Page<User> findByFullNameContainingIgnoreCase(String keyword, Pageable pageable);
}