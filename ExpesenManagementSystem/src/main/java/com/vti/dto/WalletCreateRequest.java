package com.vti.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletCreateRequest {

    @NotBlank(message = "Tên ví không được để trống")
    @Size(max = 50, message = "Tên ví tối đa 50 ký tự")
    private String name;

    @NotBlank(message = "Mã tiền tệ không được để trống")
    @Size(min = 3, max = 3, message = "Mã tiền tệ phải có 3 ký tự")
    private String currency;

    @NotNull(message = "Số dư không được null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Số dư phải >= 0")
    private BigDecimal balance;
}