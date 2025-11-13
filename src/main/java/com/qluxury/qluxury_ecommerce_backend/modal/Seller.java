package com.qluxury.qluxury_ecommerce_backend.modal;

import com.qluxury.qluxury_ecommerce_backend.domain.AccountStatus;
import com.qluxury.qluxury_ecommerce_backend.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address(); // Địa chỉ lấy hàng

    private String GSTIN; // ( maSố nhận dạng thuế hàng hóa và dịch vụ)

    private USER_ROLE role=USER_ROLE.ROLE_SELLER;

    private boolean isEmailVerified =false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
