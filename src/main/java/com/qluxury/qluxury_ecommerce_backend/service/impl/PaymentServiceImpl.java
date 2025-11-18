package com.qluxury.qluxury_ecommerce_backend.service.impl;

import com.qluxury.qluxury_ecommerce_backend.domain.PaymentOrderStatus;
import com.qluxury.qluxury_ecommerce_backend.domain.PaymentStatus;
import com.qluxury.qluxury_ecommerce_backend.modal.Order;
import com.qluxury.qluxury_ecommerce_backend.modal.PaymentOrder;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.repository.OrderRepository;
import com.qluxury.qluxury_ecommerce_backend.repository.PaymentOrderRepository;
import com.qluxury.qluxury_ecommerce_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final OrderRepository orderRepository;


    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        long amount = orders.stream()
                .mapToLong(Order::getTotalSellingPrice)
                .sum();

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);
        paymentOrder.setStatus(PaymentOrderStatus.PENDING); // sẽ đánh dấu SUCCESS ở controller

        return paymentOrderRepository.save(paymentOrder);
    }


    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        return paymentOrderRepository.findById(id)
                .orElseThrow(() -> new Exception("PaymentOrder not found"));
    }
}
