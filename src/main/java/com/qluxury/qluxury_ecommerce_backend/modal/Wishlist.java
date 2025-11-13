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
@EqualsAndHashCode
public class Wishlist {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private Set<Product> products = new HashSet<>(); // Sử dụng Set để tránh trùng lặp sản phẩm
}
