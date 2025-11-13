package com.qluxury.qluxury_ecommerce_backend.repository;

import com.qluxury.qluxury_ecommerce_backend.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByEmail(String email);
    VerificationCode findByOtp(String otp);
}
