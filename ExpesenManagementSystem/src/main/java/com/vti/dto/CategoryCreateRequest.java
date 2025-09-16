package com.vti.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    @NotNull(message = "ID ví không được null")
    private Long walletId;

    private Long parentId;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 120, message = "Tên danh mục tối đa 120 ký tự")
    private String name;

    @NotBlank(message = "Loại danh mục không được để trống")
    @Pattern(regexp = "INCOME|EXPENSE|TRANSFER", message = "Loại danh mục phải là INCOME, EXPENSE hoặc TRANSFER")
    private String type;

    @Size(max = 7, message = "Mã màu tối đa 7 ký tự")
    private String color;

    @Min(value = 0, message = "Thứ tự sắp xếp phải >= 0")
    private int sortOrder;
}