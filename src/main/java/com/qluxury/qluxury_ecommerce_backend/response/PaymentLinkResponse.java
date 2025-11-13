package com.qluxury.qluxury_ecommerce_backend.response;

import lombok.Data;

@Data
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;
}
