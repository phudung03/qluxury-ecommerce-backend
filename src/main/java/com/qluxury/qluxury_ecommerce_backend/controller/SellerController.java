package com.qluxury.qluxury_ecommerce_backend.controller;

import com.qluxury.qluxury_ecommerce_backend.domain.AccountStatus;
import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import com.qluxury.qluxury_ecommerce_backend.exceptions.SellerException;
import com.qluxury.qluxury_ecommerce_backend.modal.Seller;
import com.qluxury.qluxury_ecommerce_backend.modal.SellerReport;
import com.qluxury.qluxury_ecommerce_backend.modal.VerificationCode;
import com.qluxury.qluxury_ecommerce_backend.repository.VerificationCodeRepository;
import com.qluxury.qluxury_ecommerce_backend.request.LoginRequest;
import com.qluxury.qluxury_ecommerce_backend.response.ApiResponse;
import com.qluxury.qluxury_ecommerce_backend.response.AuthResponse;
import com.qluxury.qluxury_ecommerce_backend.response.SignupRequest;
import com.qluxury.qluxury_ecommerce_backend.service.AuthService;
import com.qluxury.qluxury_ecommerce_backend.service.EmailService;
import com.qluxury.qluxury_ecommerce_backend.service.SellerReportService;
import com.qluxury.qluxury_ecommerce_backend.service.SellerService;
import com.qluxury.qluxury_ecommerce_backend.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }
    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySeller(@PathVariable String otp) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("wrong otp...");
        }
        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(),otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception, MessagingException {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Qluxury Email Verification Code";
        String text = "Welcome to Qluxury! verify your account using this link ";
        String frontend_url="http://localhost:3000/seller/verify-email/";
        emailService.sendVerificationOtpEmail(seller.getEmail(),verificationCode.getOtp(),subject,text + frontend_url);

        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false)AccountStatus status){
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt, @RequestBody Seller seller)
            throws Exception{
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
