package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
