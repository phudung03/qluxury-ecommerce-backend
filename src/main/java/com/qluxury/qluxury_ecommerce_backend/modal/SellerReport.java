package com.qluxury.qluxury_ecommerce_backend.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerReport {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings=0L; // Tổng thu nhập của người bán

    private Long totalSales=0L; // Tổng số đơn hàng đã bán được

    private Long totalRefunds=0L; // Tổng số đơn hàng đã hoàn trả

    private Long totalTax = 0L; // Tổng số thuế đã thu

    private Long netEarnings = 0L; // Thu nhập ròng sau thuế

    private Integer totalOrders = 0; // Tổng số đơn hàng đã xử lý

    private Integer cancelledOrders = 0; // Tổng số đơn hàng đã bị hủy

    private Integer totalTransactions = 0; // Tổng số giao dịch đã thực hiện
}
