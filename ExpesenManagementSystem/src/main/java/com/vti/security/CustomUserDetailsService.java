//package com.vti.security;
//
//import com.vti.entity.User;
//import com.vti.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Nếu bạn dùng email làm username, dùng findByEmail
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user: " + username));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())               // hoặc user.getUsername() nếu có field riêng
//                .password(user.getPasswordHash())                // đã mã hóa (BCrypt)
//                .authorities(
//                        user.getRoles().stream()
//                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                                .collect(Collectors.toList())
//                )
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)                              // đổi theo trạng thái user nếu có
//                .build();
//    }
//}