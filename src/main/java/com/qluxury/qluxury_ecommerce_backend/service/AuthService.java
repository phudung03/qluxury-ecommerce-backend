package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import com.qluxury.qluxury_ecommerce_backend.request.LoginRequest;
import com.qluxury.qluxury_ecommerce_backend.response.AuthResponse;
import com.qluxury.qluxury_ecommerce_backend.response.SignupRequest;


public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
}
