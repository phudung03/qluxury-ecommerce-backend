package com.qluxury.qluxury_ecommerce_backend.request;

import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
