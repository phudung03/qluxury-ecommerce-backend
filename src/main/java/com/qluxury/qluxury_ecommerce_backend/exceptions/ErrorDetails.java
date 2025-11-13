package com.qluxury.qluxury_ecommerce_backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String error;
    private String details;
    private LocalDateTime timestamp;
}
