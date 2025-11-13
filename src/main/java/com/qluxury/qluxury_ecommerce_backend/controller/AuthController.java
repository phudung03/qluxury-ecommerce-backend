package com.qluxury.qluxury_ecommerce_backend.controller;

import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.modal.VerificationCode;
import com.qluxury.qluxury_ecommerce_backend.repository.UserRepository;
import com.qluxury.qluxury_ecommerce_backend.request.LoginOtpRequest;
import com.qluxury.qluxury_ecommerce_backend.request.LoginRequest;
import com.qluxury.qluxury_ecommerce_backend.response.ApiResponse;
import com.qluxury.qluxury_ecommerce_backend.response.AuthResponse;
import com.qluxury.qluxury_ecommerce_backend.response.SignupRequest;
import com.qluxury.qluxury_ecommerce_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }
    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOtp(req.getEmail(), req.getRole());

        ApiResponse res = new ApiResponse();

        res.setMessage("otp sent successfully");

        return ResponseEntity.ok(res);
    }
    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.signing(req);

        return ResponseEntity.ok(authResponse);
    }
}
