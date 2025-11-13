package com.qluxury.qluxury_ecommerce_backend.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore // tránh vòng lặp vô hạn khi serializing đối tượng CartItem thành JSON
    private Cart cart;
    @ManyToOne
    private Product product;

    private String size;

    private int quantity=1;

    private Integer mrpPrice; // Giá MRP của sản phẩm tại thời điểm thêm vào giỏ hàng

    private Integer sellingPrice; // Giá bán của sản phẩm tại thời điểm thêm vào giỏ hàng

    private Long userId;
}
