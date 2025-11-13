package com.qluxury.qluxury_ecommerce_backend.repository;

import com.qluxury.qluxury_ecommerce_backend.modal.Cart;
import com.qluxury.qluxury_ecommerce_backend.modal.CartItem;
import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product,String size);
}
