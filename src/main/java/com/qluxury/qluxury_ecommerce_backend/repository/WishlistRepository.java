package com.qluxury.qluxury_ecommerce_backend.repository;

import com.qluxury.qluxury_ecommerce_backend.modal.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUserId(Long userId);
}
