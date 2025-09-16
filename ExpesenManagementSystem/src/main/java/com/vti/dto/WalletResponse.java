package com.vti.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {
    private Long id;
    private String name;
    private String currency;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}