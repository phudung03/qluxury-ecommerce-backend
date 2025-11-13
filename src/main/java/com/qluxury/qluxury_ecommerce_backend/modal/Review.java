package com.qluxury.qluxury_ecommerce_backend.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Review {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)// Noi dung đánh giá không được để trống
    private String reviewerText;

    @Column(nullable = false)// Đánh giá không được để trống
    private double rating;

    @ElementCollection // Sử dụng @ElementCollection để lưu trữ danh sách các hình ảnh dưới dạng các phần tử cơ bản
    private List<String> productImages;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;

    @Column(nullable = false) // Thời gian tạo đánh giá không được để trống
    private LocalDateTime createdAt = LocalDateTime.now();
}
