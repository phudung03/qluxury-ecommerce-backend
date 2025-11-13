package com.qluxury.qluxury_ecommerce_backend.modal;

import lombok.Data;

@Data
public class BankDetails {
    private String accountNumber; // Số tài khoản
    private String accountHolderName; // Tên chủ tài khoản
    //private String bankName; // Tên ngân hàng
    private String ifscCode; // Mã hệ thống tài chính
}
