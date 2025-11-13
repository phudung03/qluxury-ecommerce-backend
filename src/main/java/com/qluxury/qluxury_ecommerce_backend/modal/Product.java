package com.qluxury.qluxury_ecommerce_backend.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercentage;
    private int quantity;
    private String color;

    @ElementCollection // Sử dụng @ElementCollection để lưu trữ danh sách các hình ảnh dưới dạng các phần tử cơ bản
    private List<String> images = new ArrayList<>();

    private int numRatings;
    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt;
    private String Sizes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval = true để xóa đánh giá khi sản phẩm bị xóa
    private List<Review> reviews = new ArrayList<>();
}
