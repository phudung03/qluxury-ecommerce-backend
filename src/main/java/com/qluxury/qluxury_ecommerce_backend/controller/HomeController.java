package com.qluxury.qluxury_ecommerce_backend.controller;

import com.qluxury.qluxury_ecommerce_backend.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
     @GetMapping
        public ApiResponse HomeControllerHandler() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Welcome to QLuxury E-commerce System!");
            return apiResponse;
        }
}
