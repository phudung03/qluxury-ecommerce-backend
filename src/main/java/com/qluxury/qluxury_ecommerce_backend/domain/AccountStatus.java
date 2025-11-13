package com.qluxury.qluxury_ecommerce_backend.domain;

public enum AccountStatus {
    PENDING_VERIFICATION,// chờ xác minh
    ACTIVE, // tài khoản hoạt động bình thường
    SUSPENDED, // tạm ngưng hoạt động
    DEACTIVATED, // đã hủy kích hoạt
    BANNED, // bị cấm vĩnh viễn
    CLOSED // tài khoản đã đóng
}
