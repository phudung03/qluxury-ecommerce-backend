package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.Order;
import com.qluxury.qluxury_ecommerce_backend.modal.PaymentOrder;
import com.qluxury.qluxury_ecommerce_backend.modal.User;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
}

