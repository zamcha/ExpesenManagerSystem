package com.vti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @NotBlank(message = "Tên quyền không được để trống")
    @Size(max = 50, message = "Tên quyền tối đa 50 ký tự")
    private String name;
}
