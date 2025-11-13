package com.qluxury.qluxury_ecommerce_backend.service.impl;

import com.qluxury.qluxury_ecommerce_backend.modal.Seller;
import com.qluxury.qluxury_ecommerce_backend.modal.SellerReport;
import com.qluxury.qluxury_ecommerce_backend.repository.SellerReportRepository;
import com.qluxury.qluxury_ecommerce_backend.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if(sr==null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
