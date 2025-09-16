package com.vti.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateRequest {

    @NotNull(message = "ID ví không được null")
    private Long walletId;

    @NotNull(message = "ID người dùng không được null")
    private Long userId;

    private Long categoryId;

    @NotBlank(message = "Loại giao dịch không được để trống")
    @Pattern(regexp = "INCOME|EXPENSE|TRANSFER", message = "Loại giao dịch phải là INCOME, EXPENSE hoặc TRANSFER")
    private String type;

    @NotNull(message = "Số tiền không được null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Số tiền phải > 0")
    private BigDecimal amount;

    @NotNull(message = "Ngày giao dịch không được null")
    private LocalDateTime occurredAt;

    @Size(max = 1000, message = "Ghi chú tối đa 1000 ký tự")
    private String note;

    @Size(max = 255, message = "Địa điểm tối đa 255 ký tự")
    private String location;
}