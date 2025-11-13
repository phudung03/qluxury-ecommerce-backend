package com.qluxury.qluxury_ecommerce_backend.request;

import lombok.Data;

import java.util.List;
@Data
public class CreateReviewRequest {
    private String reviewText;
    private double reviewRating;
    private List<String> productImage;
}
