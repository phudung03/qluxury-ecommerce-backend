package com.qluxury.qluxury_ecommerce_backend.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval = true để xóa các mục giỏ hàng khi giỏ hàng bị xóa
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalSellingPrice;

    private int totalItems;

    private int totalMrpPrice; // Tổng giá trị MRP của tất cả các sản phẩm trong giỏ hàng

    private int discount;

    private String couponCode;
}
