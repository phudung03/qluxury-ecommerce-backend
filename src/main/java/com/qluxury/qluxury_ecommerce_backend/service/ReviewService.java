package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.Product;
import com.qluxury.qluxury_ecommerce_backend.modal.Review;
import com.qluxury.qluxury_ecommerce_backend.modal.User;
import com.qluxury.qluxury_ecommerce_backend.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;

    void deleteReview(Long reviewId,Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
