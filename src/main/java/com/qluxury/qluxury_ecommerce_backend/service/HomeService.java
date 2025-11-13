package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.Home;
import com.qluxury.qluxury_ecommerce_backend.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
