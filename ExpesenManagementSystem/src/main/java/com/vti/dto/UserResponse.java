package com.vti.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String status;
    private LocalDateTime createdAt;
    private Set<RoleResponse> roles;
}