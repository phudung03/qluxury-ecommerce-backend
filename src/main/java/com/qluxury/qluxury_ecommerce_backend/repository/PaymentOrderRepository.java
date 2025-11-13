package com.qluxury.qluxury_ecommerce_backend.repository;

import com.qluxury.qluxury_ecommerce_backend.modal.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLinkId(String paymentId);
}
