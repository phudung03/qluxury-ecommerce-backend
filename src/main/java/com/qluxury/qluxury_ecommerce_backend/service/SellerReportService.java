package com.qluxury.qluxury_ecommerce_backend.service;

import com.qluxury.qluxury_ecommerce_backend.modal.Seller;
import com.qluxury.qluxury_ecommerce_backend.modal.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
