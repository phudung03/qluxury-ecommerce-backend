package com.qluxury.qluxury_ecommerce_backend.response;

import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
