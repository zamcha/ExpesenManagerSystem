package com.vti.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private String userFullName;
    private String type;
    private BigDecimal amount;
    private LocalDateTime occurredAt;
    private String note;
    private String location;
}